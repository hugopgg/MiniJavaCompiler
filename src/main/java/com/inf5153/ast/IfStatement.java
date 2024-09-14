package com.inf5153.ast;

import com.inf5153.ast.visitor.Visitor;

/**
 * Represents an "if" statement in the Abstract Syntax Tree (AST). An IfStatement consists of a condition 
 * and two blocks: one to execute if the condition is true (then block) and one to execute if the condition 
 * is false (else block). This class extends Statement and is used to model conditional execution.
 */
public class IfStatement extends Statement {
    private Condition condition;
    private Block thenBlock;
    private Block elseBlock;

    /**
     * Constructs an IfStatement with the specified condition, then block, and else block.
     *
     * @param condition The condition that determines which block of statements to execute.
     * @param thenBlock The block of statements to execute if the condition is true.
     * @param elseBlock The block of statements to execute if the condition is false, or null if there is no else block.
     */
    public IfStatement(Condition condition, Block thenBlock, Block elseBlock) {
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    /**
     * Returns the condition of this "if" statement.
     *
     * @return The condition that is evaluated to determine which block to execute.
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * Returns the block of statements to execute if the condition is true.
     *
     * @return The block of statements to execute if the condition evaluates to true.
     */
    public Block getThenBlock() {
        return thenBlock;
    }

    /**
     * Returns the block of statements to execute if the condition is false.
     *
     * @return The block of statements to execute if the condition evaluates to false, or null if there is no else block.
     */
    public Block getElseBlock() {
        return elseBlock;
    }

    /**
     * Accepts a visitor and allows it to process this "if" statement.
     *
     * @param visitor The visitor that processes this "if" statement.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a string representation of this "if" statement.
     *
     * @return A string representing this "if" statement.
     */
    @Override
    public String toString() {
        return "IfStatement";
    }
}
