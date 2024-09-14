package com.inf5153.ast;

import com.inf5153.ast.visitor.Visitor;

/**
 * Represents a binary expression in the Abstract Syntax Tree (AST).
 * A binary expression consists of an operation between two expressions,
 * connected by an operator.
 */
public class BinaryExpression extends Expression {
    private Expression left; // The left operand
    private Expression right; // The right operand
    private String operator; // The operator

    /**
     * Constructs a BinaryExpression with the given left operand, operator, and
     * right operand.
     *
     * @param left     the left operand of the binary expression
     * @param operator the operator used in the binary expression
     * @param right    the right operand of the binary expression
     */
    public BinaryExpression(Expression left, String operator, Expression right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    /**
     * Returns the left operand of the binary expression.
     *
     * @return the left operand
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * Returns the right operand of the binary expression.
     *
     * @return the right operand
     */
    public Expression getRight() {
        return right;
    }

    /**
     * Returns the operator used in the binary expression.
     *
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Accepts a visitor to visit this BinaryExpression.
     *
     * @param visitor the visitor to accept
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a string representation of the binary expression.
     * This representation includes the operator used.
     *
     * @return a string representing the binary expression
     */
    @Override
    public String toString() {
        return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
    }

    // @Override
    // public String toString() {
    //     return "BinaryExpression: (" + operator + ")";
    // }
}
