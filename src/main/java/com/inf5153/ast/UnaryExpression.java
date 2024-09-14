package com.inf5153.ast;

import com.inf5153.ast.visitor.Visitor;

/**
 * Represents a unary expression in the Abstract Syntax Tree (AST).
 * A unary expression consists of a single expression with a preceding operator.
 */
public class UnaryExpression extends Expression {
    private Expression expression; // The expression to which the operator is applied
    private String operator; // The unary operator

    /**
     * Constructs a UnaryExpression with the given expression and operator.
     *
     * @param expression the expression to which the operator is applied
     * @param operator   the unary operator
     */
    public UnaryExpression(Expression expression, String operator) {
        this.expression = expression;
        this.operator = operator;
    }

    /**
     * Returns the expression to which the unary operator is applied.
     *
     * @return the expression
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Returns the unary operator used in the unary expression.
     *
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Accepts a visitor to visit this UnaryExpression.
     *
     * @param visitor the visitor to accept
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a string representation of the unary expression.
     * This representation includes the operator used.
     *
     * @return a string representing the unary expression
     */
    @Override
    public String toString() {
        return operator + expression.toString();
    }

    // @Override
    // public String toString() {
    // return "UnaryExpression: " + operator;
    // }
}
