package com.inf5153.ast.visitor;

import com.inf5153.ast.*;


/**
 * Defines a visitor interface for the Visitor design pattern.
 * This interface provides visit methods for each type of node in the Abstract Syntax Tree (AST).
 * Implementations of this interface will define specific operations to be performed on each type of AST node.
 */
public interface Visitor {
    
    /**
     * Visits an {@code Assignment} node.
     *
     * @param assignment the assignment node to visit
     */
    void visit(Assignment assignment);

    /**
     * Visits an {@code IfStatement} node.
     *
     * @param ifStatement the if statement node to visit
     */
    void visit(IfStatement ifStatement);

    /**
     * Visits a {@code WhileStatement} node.
     *
     * @param whileStatement the while statement node to visit
     */
    void visit(WhileStatement whileStatement);

    /**
     * Visits a {@code PrintStatement} node.
     *
     * @param printStatement the print statement node to visit
     */
    void visit(PrintStatement printStatement);

    /**
     * Visits a {@code ReadStatement} node.
     *
     * @param readStatement the read statement node to visit
     */
    void visit(ReadStatement readStatement);

    /**
     * Visits a {@code Block} node.
     *
     * @param block the block node to visit
     */
    void visit(Block block);

    /**
     * Visits a {@code LiteralNumber} node.
     *
     * @param literalNumber the literal number node to visit
     */
    void visit(LiteralNumber literalNumber);

    /**
     * Visits a {@code LiteralString} node.
     *
     * @param literalString the literal string node to visit
     */
    void visit(LiteralString literalString);

    /**
     * Visits an {@code Identifier} node.
     *
     * @param identifier the identifier node to visit
     */
    void visit(Identifier identifier);

    /**
     * Visits a {@code BinaryExpression} node.
     *
     * @param binaryExpression the binary expression node to visit
     */
    void visit(BinaryExpression binaryExpression);

    /**
     * Visits a {@code UnaryExpression} node.
     *
     * @param unaryExpression the unary expression node to visit
     */
    void visit(UnaryExpression unaryExpression);

    /**
     * Visits a {@code Condition} node.
     *
     * @param condition the condition node to visit
     */
    void visit(Condition condition);
}
