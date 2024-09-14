package com.inf5153.ast;

import java.util.List;

import com.inf5153.ast.visitor.Visitor;

/**
 * Represents a block of statements in the Abstract Syntax Tree (AST).
 * A Block is a container that holds multiple statements, typically enclosed in curly braces.
 */
public class Block extends Statement {
    private List<Statement> statements;  // List of statements within the block

    /**
     * Constructs a Block with the given list of statements.
     *
     * @param statements the list of statements contained in the block
     */
    public Block(List<Statement> statements) {
        this.statements = statements;
    }

    /**
     * Returns the list of statements contained in the block.
     *
     * @return the list of statements
     */
    public List<Statement> getStatements() {
        return statements;
    }

    /**
     * Accepts a visitor to visit this Block.
     *
     * @param visitor the visitor to accept
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a string representation of the Block.
     * This representation includes the number of statements within the block.
     *
     * @return a string representation of the Block
     */
    @Override
    public String toString() {
        return "Block";
    }
}
