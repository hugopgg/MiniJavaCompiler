package com.inf5153.ast;

import com.inf5153.ast.visitor.Visitor;

/**
 * Represents a print statement in the Abstract Syntax Tree (AST).
 * A print statement outputs the value of an expression.
 */
public class PrintStatement extends Statement {
    private Expression expression; // The expression to be printed

    /**
     * Constructs a PrintStatement with the given expression.
     *
     * @param expression The expression to be printed.
     */
    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    /**
     * Returns the expression associated with this print statement.
     *
     * @return The expression to be printed.
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Accepts a visitor to visit this PrintStatement.
     *
     * @param visitor The visitor to accept.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a string representation of the print statement.
     *
     * @return A string representing the print statement.
     */
    @Override
    public String toString() {
        return "PrintStatement";
    }
}
