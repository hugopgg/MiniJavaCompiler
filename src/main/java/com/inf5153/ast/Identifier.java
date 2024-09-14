package com.inf5153.ast;

import com.inf5153.ast.visitor.Visitor;

/**
 * Represents an identifier in the Abstract Syntax Tree (AST).
 * An identifier is typically used to denote a variable or a symbolic name.
 */
public class Identifier extends Expression {
    private String name; // The name of the identifier

    /**
     * Constructs an Identifier with the specified name.
     *
     * @param name the name of the identifier
     */
    public Identifier(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the identifier.
     *
     * @return the name of the identifier
     */
    public String getName() {
        return name;
    }

    /**
     * Accepts a visitor to visit this Identifier.
     *
     * @param visitor the visitor to accept
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a string representation of the identifier.
     * This representation includes the name of the identifier.
     *
     * @return a string representing the identifier
     */
    @Override
    public String toString() {
        return name;
    }

    // @Override
    // public String toString() {
    // return "Identifier: " + name;
    // }
}
