/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.util.deparser;

import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.WithItem;

import java.util.Iterator;

import static java.util.stream.Collectors.joining;

public class DeleteDeParser extends AbstractDeParser<Delete> {

    private ExpressionVisitor<StringBuilder> expressionVisitor =
            new ExpressionVisitorAdapter<StringBuilder>();

    public DeleteDeParser() {
        super(new StringBuilder());
    }

    public DeleteDeParser(ExpressionVisitor<StringBuilder> expressionVisitor,
            StringBuilder buffer) {
        super(buffer);
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    @SuppressWarnings({"PMD.CyclomaticComplexity", "PMD.NPathComplexity"})
    public void deParse(Delete delete) {
        if (delete.getWithItemsList() != null && !delete.getWithItemsList().isEmpty()) {
            builder.append("WITH ");
            for (Iterator<WithItem<?>> iter = delete.getWithItemsList().iterator(); iter
                    .hasNext();) {
                WithItem<?> withItem = iter.next();
                builder.append(withItem);
                if (iter.hasNext()) {
                    builder.append(",");
                }
                builder.append(" ");
            }
        }
        builder.append("DELETE");
        if (delete.getOracleHint() != null) {
            builder.append(delete.getOracleHint()).append(" ");
        }
        if (delete.getModifierPriority() != null) {
            builder.append(" ").append(delete.getModifierPriority());
        }
        if (delete.isModifierQuick()) {
            builder.append(" QUICK");
        }
        if (delete.isModifierIgnore()) {
            builder.append(" IGNORE");
        }
        if (delete.getTables() != null && !delete.getTables().isEmpty()) {
            builder.append(
                    delete.getTables().stream().map(Table::getFullyQualifiedName)
                            .collect(joining(", ", " ", "")));
        }

        if (delete.getOutputClause() != null) {
            delete.getOutputClause().appendTo(builder);
        }

        if (delete.isHasFrom()) {
            builder.append(" FROM");
        }
        builder.append(" ").append(delete.getTable().toString());

        if (delete.getUsingList() != null && !delete.getUsingList().isEmpty()) {
            builder.append(" USING").append(
                    delete.getUsingList().stream().map(Table::toString)
                            .collect(joining(", ", " ", "")));
        }
        if (delete.getJoins() != null) {
            for (Join join : delete.getJoins()) {
                if (join.isSimple()) {
                    builder.append(", ").append(join);
                } else {
                    builder.append(" ").append(join);
                }
            }
        }

        deparseWhereClause(delete);

        if (delete.getPreferringClause() != null) {
            builder.append(" ").append(delete.getPreferringClause());
        }
        if (delete.getOrderByElements() != null) {
            new OrderByDeParser(expressionVisitor, builder).deParse(delete.getOrderByElements());
        }
        if (delete.getLimit() != null) {
            new LimitDeparser(expressionVisitor, builder).deParse(delete.getLimit());
        }

        if (delete.getReturningClause() != null) {
            delete.getReturningClause().appendTo(builder);
        }

    }

    protected void deparseWhereClause(Delete delete) {
        if (delete.getWhere() != null) {
            builder.append(" WHERE ");
            delete.getWhere().accept(expressionVisitor, null);
        }
    }

    public ExpressionVisitor<StringBuilder> getExpressionVisitor() {
        return expressionVisitor;
    }

    public void setExpressionVisitor(ExpressionVisitor<StringBuilder> visitor) {
        expressionVisitor = visitor;
    }
}
