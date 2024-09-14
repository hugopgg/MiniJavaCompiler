package com.inf5153.ast;

import com.inf5153.ast.visitor.Visitor;

/**
 * Represents a literal number in the Abstract Syntax Tree (AST).
 * A literal number is an expression that holds an integer value.
 */
public class LiteralNumber extends Expression {
    private int value; // The integer value of the literal number

    /**
     * Constructs a LiteralNumber with the specified value.
     *
     * @param value The integer value of the literal number.
     */
    public LiteralNumber(int value) {
        this.value = value;
    }

    /**
     * Returns the integer value of the literal number.
     *
     * @return The integer value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Accepts a visitor to visit this LiteralNumber.
     *
     * @param visitor The visitor to accept.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a string representation of the literal number.
     *
     * @return A string representing the literal number.
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    // @Override
    // public String toString() {
    // return "LiteralNumber: " + value;
    // }
}
