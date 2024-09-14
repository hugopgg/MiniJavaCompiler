package com.inf5153.ast;

import com.inf5153.ast.visitor.Visitor;

/**
 * Represents a conditional expression in the Abstract Syntax Tree (AST).
 * A condition consists of two expressions and an operator that defines 
 * the relationship between these expressions (e.g., ==, !=, <, >).
 */
public class Condition extends Node {
    private Expression left;    // The left operand of the condition
    private Expression right;   // The right operand of the condition
    private String operator;    // The operator used in the condition (e.g., ==, !=, <, >)

    /**
     * Constructs a Condition with the given left operand, operator, and right operand.
     *
     * @param left the left operand of the condition
     * @param operator the operator used in the condition
     * @param right the right operand of the condition
     */
    public Condition(Expression left, String operator, Expression right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    /**
     * Returns the left operand of the condition.
     *
     * @return the left operand
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * Returns the right operand of the condition.
     *
     * @return the right operand
     */
    public Expression getRight() {
        return right;
    }

    /**
     * Returns the operator used in the condition.
     *
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Accepts a visitor to visit this Condition.
     *
     * @param visitor the visitor to accept
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a string representation of the condition.
     * This representation includes the operator used.
     *
     * @return a string representing the condition
     */
    @Override
    public String toString() {
        return "Condition: " + operator;
    }
}
