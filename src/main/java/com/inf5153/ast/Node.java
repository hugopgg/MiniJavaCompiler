package com.inf5153.ast;

import com.inf5153.ast.visitor.Visitor;

/**
 * Represents a node in the Abstract Syntax Tree (AST).
 * This is an abstract base class for all nodes in the AST,
 * defining the common interface for visitor pattern support.
 */
public abstract class Node {

    /**
     * Accepts a visitor to visit this node.
     * This method is used to implement the Visitor design pattern,
     * allowing different operations to be performed on the AST nodes.
     *
     * @param visitor The visitor that will process this node.
     */
    public abstract void accept(Visitor visitor);
}
