package com.inf5153.ast;

import com.inf5153.ast.visitor.Visitor;

/**
 * Represents a literal string in the Abstract Syntax Tree (AST).
 * A literal string is an expression that holds a string value.
 */
public class LiteralString extends Expression {
    private String value; // The string value of the literal string

    /**
     * Constructs a LiteralString with the specified value.
     *
     * @param value The string value of the literal string.
     */
    public LiteralString(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of the literal string.
     *
     * @return The string value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Accepts a visitor to visit this LiteralString.
     *
     * @param visitor The visitor to accept.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a string representation of the literal string.
     *
     * @return A string representing the literal string.
     */
    @Override
    public String toString() {
        return "\"" + value + "\"";
    }
}
