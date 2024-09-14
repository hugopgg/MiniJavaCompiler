package com.inf5153.ast;

import com.inf5153.ast.visitor.Visitor;

/**
 * Represents a while loop statement in the Abstract Syntax Tree (AST).
 * A while statement consists of a condition and a block of statements.
 */
public class WhileStatement extends Statement {
    private Condition condition; // The condition to evaluate for the while loop
    private Block block;         // The block of statements to execute while the condition is true

    /**
     * Constructs a WhileStatement with the given condition and block.
     *
     * @param condition the condition to evaluate for the while loop
     * @param block     the block of statements to execute while the condition is true
     */
    public WhileStatement(Condition condition, Block block) {
        this.condition = condition;
        this.block = block;
    }

    /**
     * Returns the condition of the while statement.
     *
     * @return the condition
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * Returns the block of statements to execute while the condition is true.
     *
     * @return the block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Accepts a visitor to visit this WhileStatement.
     *
     * @param visitor the visitor to accept
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a string representation of the while statement.
     *
     * @return a string representing the while statement
     */
    @Override
    public String toString() {
        return "WhileStatement";
    }
}
