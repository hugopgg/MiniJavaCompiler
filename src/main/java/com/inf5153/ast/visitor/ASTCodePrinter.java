package com.inf5153.ast.visitor;

import java.util.ArrayList;
import java.util.List;

import com.inf5153.ast.*;


/**
 * The ASTCodePrinter class implements the Visitor pattern to print the Abstract
 * Syntax Tree (AST) nodes in a formatted and indented manner. It also maintains
 * a list of nodes encountered during the traversal.
 */
public class ASTCodePrinter implements Visitor {

    private int indentationLevel = 0; // Current level of indentation for printing
    private List<Node> nodeList = new ArrayList<>(); // List of nodes visited during the traversal

    /**
     * Visits an Assignment node and prints its representation.
     * The format is "identifier = expression;".
     *
     * @param assignment the Assignment node to visit
     */
    @Override
    public void visit(Assignment assignment) {
        addNode(assignment);
        printIndentation();
        System.out.print(assignment.getIdentifier().getName() + " = ");
        assignment.getExpression().accept(this);
        System.out.println(";");
    }

    /**
     * Visits a BinaryExpression node and prints its representation.
     * The format is "(leftOperand operator rightOperand)".
     *
     * @param binaryExpression the BinaryExpression node to visit
     */
    @Override
    public void visit(BinaryExpression binaryExpression) {
        addNode(binaryExpression);
        System.out.print("(");
        binaryExpression.getLeft().accept(this);
        System.out.print(" " + binaryExpression.getOperator() + " ");
        binaryExpression.getRight().accept(this);
        System.out.print(")");
    }

    /**
     * Visits a Block node and prints its representation.
     * Blocks are surrounded by curly braces and contain a list of statements.
     *
     * @param block the Block node to visit
     */
    @Override
    public void visit(Block block) {
        addNode(block);
        printIndentation();
        System.out.println("{");
        indentationLevel++;
        for (Statement statement : block.getStatements()) {
            statement.accept(this);
        }
        indentationLevel--;
        printIndentation();
        System.out.println("}");
    }

    /**
     * Visits a Condition node and prints its representation.
     * The format is "(leftOperand operator rightOperand)".
     *
     * @param condition the Condition node to visit
     */
    @Override
    public void visit(Condition condition) {
        addNode(condition);
        System.out.print("(");
        condition.getLeft().accept(this);
        System.out.print(" " + condition.getOperator() + " ");
        condition.getRight().accept(this);
        System.out.print(")");
    }

    /**
     * Visits an Identifier node and prints its representation.
     * The identifier is printed as-is.
     *
     * @param identifier the Identifier node to visit
     */
    @Override
    public void visit(Identifier identifier) {
        addNode(identifier);
        System.out.print(identifier.getName());
    }

    /**
     * Visits an IfStatement node and prints its representation.
     * The format is "if (condition) then { ... } [else { ... }]".
     *
     * @param ifStatement the IfStatement node to visit
     */
    @Override
    public void visit(IfStatement ifStatement) {
        addNode(ifStatement);
        printIndentation();
        System.out.print("if (");
        ifStatement.getCondition().accept(this);
        System.out.println(") then ");
        indentationLevel++;
        ifStatement.getThenBlock().accept(this);
        indentationLevel--;
        if (ifStatement.getElseBlock() != null) {
            printIndentation();
            System.out.println("else ");
            indentationLevel++;
            ifStatement.getElseBlock().accept(this);
            indentationLevel--;
        }
    }

    /**
     * Visits a LiteralNumber node and prints its representation.
     * The literal number is printed as-is.
     *
     * @param literalNumber the LiteralNumber node to visit
     */
    @Override
    public void visit(LiteralNumber literalNumber) {
        addNode(literalNumber);
        System.out.print(literalNumber.getValue());
    }

    /**
     * Visits a LiteralString node and prints its representation.
     * The string literal is printed with double quotes.
     *
     * @param literalString the LiteralString node to visit
     */
    @Override
    public void visit(LiteralString literalString) {
        addNode(literalString);
        System.out.print("\"" + literalString.getValue() + "\"");
    }

    /**
     * Visits a PrintStatement node and prints its representation.
     * The format is "print(expression);".
     *
     * @param printStatement the PrintStatement node to visit
     */
    @Override
    public void visit(PrintStatement printStatement) {
        addNode(printStatement);
        printIndentation();
        System.out.print("print(");
        printStatement.getExpression().accept(this);
        System.out.println(");");
    }

    /**
     * Visits a ReadStatement node and prints its representation.
     * The format is "read(\"literalString\", identifier);".
     *
     * @param readStatement the ReadStatement node to visit
     */
    @Override
    public void visit(ReadStatement readStatement) {
        addNode(readStatement);
        printIndentation();
        System.out.print("read(\"" + readStatement.getLiteralString().getValue() + "\", ");
        readStatement.getIdentifier().accept(this);
        System.out.println(");");
    }

    /**
     * Visits a UnaryExpression node and prints its representation.
     * The format is "(operator operand)".
     *
     * @param unaryExpression the UnaryExpression node to visit
     */
    @Override
    public void visit(UnaryExpression unaryExpression) {
        addNode(unaryExpression);
        System.out.print("(" + unaryExpression.getOperator());
        unaryExpression.getExpression().accept(this);
        System.out.print(")");
    }

    /**
     * Visits a WhileStatement node and prints its representation.
     * The format is "while (condition) { ... }".
     *
     * @param whileStatement the WhileStatement node to visit
     */
    @Override
    public void visit(WhileStatement whileStatement) {
        addNode(whileStatement);
        printIndentation();
        System.out.print("while (");
        whileStatement.getCondition().accept(this);
        System.out.println(") ");
        indentationLevel++;
        whileStatement.getBlock().accept(this);
        indentationLevel--;
    }

    /**
     * Prints the current indentation level.
     * Indentation is used to format the output in a readable manner.
     */
    private void printIndentation() {
        for (int i = 0; i < indentationLevel; i++) {
            System.out.print("  ");
        }
    }

    /**
     * Adds a node to the list of nodes visited.
     * This list is used for printing the node details.
     *
     * @param nodeDescription the node to add to the list
     */
    private void addNode(Node nodeDescription) {
        nodeList.add(nodeDescription);
    }

    /**
     * Prints the list of nodes visited during the traversal.
     * Each node is printed with its string representation.
     */
    public void printNodeList() {
        System.out.println("\nNodes list:");
        for (Node node : nodeList) {
            System.out.println(node.toString());
        }
    }
}
