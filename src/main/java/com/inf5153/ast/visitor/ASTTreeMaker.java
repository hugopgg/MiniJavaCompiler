package com.inf5153.ast.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inf5153.ast.Assignment;
import com.inf5153.ast.BinaryExpression;
import com.inf5153.ast.Block;
import com.inf5153.ast.Condition;
import com.inf5153.ast.Identifier;
import com.inf5153.ast.IfStatement;
import com.inf5153.ast.LiteralNumber;
import com.inf5153.ast.LiteralString;
import com.inf5153.ast.Node;
import com.inf5153.ast.PrintStatement;
import com.inf5153.ast.ReadStatement;
import com.inf5153.ast.Statement;
import com.inf5153.ast.UnaryExpression;
import com.inf5153.ast.WhileStatement;

/**
 * The ASTTreeMaker class implements the Visitor pattern to construct and print an Abstract Syntax Tree (AST).
 * It builds a tree representation of the AST nodes and allows printing the tree structure.
 */
public class ASTTreeMaker implements Visitor {
    private Map<Node, List<Node>> tree;  // Map to hold the parent-child relationships between nodes
    private Node root;  // The root node of the AST

    /**
     * Initializes the ASTTreeMaker with an empty tree.
     */
    public ASTTreeMaker() {
        tree = new HashMap<>();
    }

    /**
     * Visits a Block node and adds its statements as children.
     * The Block node itself is set as the root if it is the first node visited.
     *
     * @param block the Block node to visit
     */
    @Override
    public void visit(Block block) {
        if (root == null) {
            root = block;
        }
        for (Statement statement : block.getStatements()) {
            addChild(block, statement);
            statement.accept(this);
        }
    }

    /**
     * Visits an Assignment node and adds its components (identifier and expression) as children.
     *
     * @param assignment the Assignment node to visit
     */
    @Override
    public void visit(Assignment assignment) {
        addChild(assignment, assignment.getIdentifier());
        addChild(assignment, assignment.getExpression());
        assignment.getIdentifier().accept(this);
        assignment.getExpression().accept(this);
    }

    /**
     * Visits a BinaryExpression node and adds its operands (left and right) as children.
     *
     * @param binaryExpression the BinaryExpression node to visit
     */
    @Override
    public void visit(BinaryExpression binaryExpression) {
        addChild(binaryExpression, binaryExpression.getLeft());
        addChild(binaryExpression, binaryExpression.getRight());
        binaryExpression.getLeft().accept(this);
        binaryExpression.getRight().accept(this);
    }

    /**
     * Visits a UnaryExpression node and adds its operand as a child.
     *
     * @param unaryExpression the UnaryExpression node to visit
     */
    @Override
    public void visit(UnaryExpression unaryExpression) {
        addChild(unaryExpression, unaryExpression.getExpression());
        unaryExpression.getExpression().accept(this);
    }

    /**
     * Visits a LiteralNumber node. This method does not add children as LiteralNumber has no children.
     *
     * @param literalNumber the LiteralNumber node to visit
     */
    @Override
    public void visit(LiteralNumber literalNumber) {
        // No children to add
    }

    /**
     * Visits a LiteralString node. This method does not add children as LiteralString has no children.
     *
     * @param literalString the LiteralString node to visit
     */
    @Override
    public void visit(LiteralString literalString) {
        // No children to add
    }

    /**
     * Visits an Identifier node. This method does not add children as Identifier has no children.
     *
     * @param identifier the Identifier node to visit
     */
    @Override
    public void visit(Identifier identifier) {
        // No children to add
    }

    /**
     * Visits a Condition node and adds its components (left and right) as children.
     *
     * @param condition the Condition node to visit
     */
    @Override
    public void visit(Condition condition) {
        addChild(condition, condition.getLeft());
        addChild(condition, condition.getRight());
        condition.getLeft().accept(this);
        condition.getRight().accept(this);
    }

    /**
     * Visits an IfStatement node and adds its components (condition, thenBlock, and optionally elseBlock) as children.
     *
     * @param ifStatement the IfStatement node to visit
     */
    @Override
    public void visit(IfStatement ifStatement) {
        addChild(ifStatement, ifStatement.getCondition());
        addChild(ifStatement, ifStatement.getThenBlock());
        if (ifStatement.getElseBlock() != null) {
            addChild(ifStatement, ifStatement.getElseBlock());
        }
        ifStatement.getCondition().accept(this);
        ifStatement.getThenBlock().accept(this);
        if (ifStatement.getElseBlock() != null) {
            ifStatement.getElseBlock().accept(this);
        }
    }

    /**
     * Visits a WhileStatement node and adds its components (condition and block) as children.
     *
     * @param whileStatement the WhileStatement node to visit
     */
    @Override
    public void visit(WhileStatement whileStatement) {
        addChild(whileStatement, whileStatement.getCondition());
        addChild(whileStatement, whileStatement.getBlock());
        whileStatement.getCondition().accept(this);
        whileStatement.getBlock().accept(this);
    }

    /**
     * Visits a PrintStatement node and adds its expression as a child.
     *
     * @param printStatement the PrintStatement node to visit
     */
    @Override
    public void visit(PrintStatement printStatement) {
        addChild(printStatement, printStatement.getExpression());
        printStatement.getExpression().accept(this);
    }

    /**
     * Visits a ReadStatement node and adds its components (literalString and identifier) as children.
     *
     * @param readStatement the ReadStatement node to visit
     */
    @Override
    public void visit(ReadStatement readStatement) {
        if (readStatement.getLiteralString() != null) {
            addChild(readStatement, readStatement.getLiteralString());
            readStatement.getLiteralString().accept(this);
        }
        addChild(readStatement, readStatement.getIdentifier());
        readStatement.getIdentifier().accept(this);
    }

    /**
     * Adds a child node to the parent node in the tree structure.
     *
     * @param parent the parent node
     * @param child  the child node to add
     */
    private void addChild(Node parent, Node child) {
        tree.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
    }

    /**
     * Prints the tree structure starting from the root node.
     * If the tree is empty, it prints a message indicating that the tree is empty.
     */
    public void printTree() {
        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }
        printNode(root, "", true);
    }

    /**
     * Prints a node and its children with appropriate indentation.
     *
     * @param node    the node to print
     * @param indent  the current indentation
     * @param isLast  boolean indicating if the node is the last child of its parent
     */
    private void printNode(Node node, String indent, boolean isLast) {
        if (node == null)
            return;

        System.out.print(indent);
        if (isLast) {
            System.out.print("└── ");
            indent += "    ";
        } else {
            System.out.print("├── ");
            indent += "│   ";
        }
        System.out.println(node.toString());

        List<Node> children = tree.get(node);
        if (children != null) {
            for (int i = 0; i < children.size(); i++) {
                printNode(children.get(i), indent, i == children.size() - 1);
            }
        }
    }
}
