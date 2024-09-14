package com.inf5153.ast;

import com.inf5153.ast.visitor.Visitor;

/**
 * The Assignment class represents an assignment statement in the Abstract
 * Syntax Tree (AST).
 * It consists of an identifier and an expression to which the identifier is
 * assigned.
 */
public class Assignment extends Statement {
    private Identifier identifier;
    private Expression expression;

    /**
     * Constructs a new Assignment instance with the specified identifier and
     * expression.
     *
     * @param identifier the identifier being assigned a value
     * @param expression the expression representing the value to be assigned
     */
    public Assignment(Identifier identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    /**
     * Returns the identifier associated with this assignment.
     *
     * @return the identifier being assigned a value
     */
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * Returns the expression representing the value to be assigned.
     *
     * @return the expression being assigned to the identifier
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Accepts a visitor to visit this assignment node.
     * This method is part of the Visitor design pattern, allowing different
     * operations
     * to be performed on this node by various visitor implementations.
     *
     * @param visitor the visitor implementing the visit method for assignment nodes
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a string representation of an assignment.
     *
     * @return a string representation of the assignment
     */
    @Override
    public String toString() {
        return identifier.getName() + " = " + expression.toString();
    }

    // @Override
    // public String toString() {
    // return "Assignment: " + identifier.getName() + " = ...";
    // }
}
