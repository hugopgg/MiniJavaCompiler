package com.inf5153.ast;

import com.inf5153.ast.visitor.Visitor;

/**
 * Represents a read statement in the Abstract Syntax Tree (AST).
 * A read statement uses a literal string as a prompt and
 * assigns the input value to an identifier.
 */
public class ReadStatement extends Statement {
    private LiteralString literalString; // The prompt message for reading input
    private Identifier identifier; // The identifier to which the input value is assigned

    /**
     * Constructs a ReadStatement with the given prompt and identifier.
     *
     * @param literalString The prompt message to be used for reading input.
     * @param identifier    The identifier to which the input value will be assigned.
     */
    public ReadStatement(LiteralString literalString, Identifier identifier) {
        this.literalString = literalString;
        this.identifier = identifier;
    }

    /**
     * Returns the prompt message associated with this read statement.
     *
     * @return The prompt message as a {@link LiteralString}.
     */
    public LiteralString getLiteralString() {
        return literalString;
    }

    /**
     * Returns the identifier associated with this read statement.
     *
     * @return The identifier to which the input value will be assigned.
     */
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * Accepts a visitor to visit this ReadStatement.
     *
     * @param visitor The visitor to accept.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a string representation of the read statement.
     *
     * @return A string representing the read statement.
     */
    @Override
    public String toString() {
        return "ReadStatement";
    }
}
