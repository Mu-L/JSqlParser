/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.statement;

import net.sf.jsqlparser.schema.Table;

public class DescribeStatement implements Statement {

    private Table table;
    private String describeType;

    public DescribeStatement() {
        // empty constructor
    }

    public DescribeStatement(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return this.describeType + " " + table.getFullyQualifiedName();
    }

    @Override
    public <T, S> T accept(StatementVisitor<T> statementVisitor, S context) {
        return statementVisitor.visit(this, context);
    }

    public DescribeStatement withTable(Table table) {
        this.setTable(table);
        return this;
    }

    public String getDescribeType() {
        return describeType;
    }

    public DescribeStatement setDescribeType(String describeType) {
        this.describeType = describeType;
        return this;
    }
}
