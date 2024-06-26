/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.statement.select;

import net.sf.jsqlparser.expression.Expression;

public interface SelectItemVisitor<T> {
    <S> T visit(SelectItem<? extends Expression> selectItem, S context);

    default void visit(SelectItem<? extends Expression> selectItem) {
        this.visit(selectItem, null);
    }
}
