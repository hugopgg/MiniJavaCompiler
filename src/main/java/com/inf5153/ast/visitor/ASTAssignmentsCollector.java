package com.inf5153.ast.visitor;

import com.inf5153.ast.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The ASTAssignmentsGenerator class is responsible for collecting all
 * assignment statements within an Abstract Syntax Tree (AST). It implements the
 * Visitor pattern to traverse the AST and gather assignments into a list.
 */
public class ASTAssignmentsCollector implements Visitor {

    private List<Assignment> assignments = new ArrayList<>();

    /**
     * Returns the list of assignments collected from the AST.
     *
     * @return a list of Assignment objects
     */
    public List<Assignment> getAssignments() {
        return assignments;
    }

    /**
     * Visits an Assignment node and adds it to the list of assignments.
     *
     * @param assignment the Assignment node to visit
     */
    @Override
    public void visit(Assignment assignment) {
        assignments.add(assignment);
    }

    /**
     * Visits a BinaryExpression node. This method is empty because
     * binary expressions are not relevant to assignment collection.
     *
     * @param binaryExpression the BinaryExpression node to visit
     */
    @Override
    public void visit(BinaryExpression binaryExpression) {
    }

    /**
     * Visits a Block node and processes each statement within the block.
     *
     * @param block the Block node to visit
     */
    @Override
    public void visit(Block block) {
        for (Statement statement : block.getStatements()) {
            statement.accept(this);
        }
    }

    /**
     * Visits a Condition node. This method is empty because
     * conditions are not relevant to assignment collection.
     *
     * @param condition the Condition node to visit
     */
    @Override
    public void visit(Condition condition) {
    }

    /**
     * Visits an Identifier node. This method is empty because
     * identifiers are not relevant to assignment collection.
     *
     * @param identifier the Identifier node to visit
     */
    @Override
    public void visit(Identifier identifier) {
    }

    /**
     * Visits an IfStatement node and processes the 'then' and 'else' blocks.
     *
     * @param ifStatement the IfStatement node to visit
     */
    @Override
    public void visit(IfStatement ifStatement) {
        ifStatement.getThenBlock().accept(this);
        if (ifStatement.getElseBlock() != null) {
            ifStatement.getElseBlock().accept(this);
        }
    }

    /**
     * Visits a LiteralNumber node. This method is empty because
     * literal numbers are not relevant to assignment collection.
     *
     * @param literalNumber the LiteralNumber node to visit
     */
    @Override
    public void visit(LiteralNumber literalNumber) {
    }

    /**
     * Visits a LiteralString node. This method is empty because
     * literal strings are not relevant to assignment collection.
     *
     * @param literalString the LiteralString node to visit
     */
    @Override
    public void visit(LiteralString literalString) {
    }

    /**
     * Visits a PrintStatement node. This method is empty because
     * print statements are not relevant to assignment collection.
     *
     * @param printStatement the PrintStatement node to visit
     */
    @Override
    public void visit(PrintStatement printStatement) {
    }

    /**
     * Visits a ReadStatement node. This method is empty because
     * read statements are not relevant to assignment collection.
     *
     * @param readStatement the ReadStatement node to visit
     */
    @Override
    public void visit(ReadStatement readStatement) {
    }

    /**
     * Visits a UnaryExpression node. This method is empty because
     * unary expressions are not relevant to assignment collection.
     *
     * @param unaryExpression the UnaryExpression node to visit
     */
    @Override
    public void visit(UnaryExpression unaryExpression) {
    }

    /**
     * Visits a WhileStatement node and processes the block within the while loop.
     *
     * @param whileStatement the WhileStatement node to visit
     */
    @Override
    public void visit(WhileStatement whileStatement) {
        whileStatement.getBlock().accept(this);
    }

    /**
     * Extracts the variables read from the expression.
     *
     * @param expression the expression from which to extract variables
     * @return a set of variable names read from the expression
     */
    private Set<String> extractVariablesFromExpression(Expression expression) {
        Set<String> variablesRead = new HashSet<>();
        if (expression instanceof Identifier) {
            variablesRead.add(((Identifier) expression).getName());
        } else if (expression instanceof BinaryExpression) {
            BinaryExpression binaryExpression = (BinaryExpression) expression;
            variablesRead.addAll(extractVariablesFromExpression(binaryExpression.getLeft()));
            variablesRead.addAll(extractVariablesFromExpression(binaryExpression.getRight()));
        }
        // Handle other expression types if needed
        return variablesRead;
    }

    /**
     * Prints out all the assignments collected from the AST in a readable format.
     */
    public void printAssignments() {
        for (Assignment assignment : assignments) {
            String variableWritten = assignment.getIdentifier().getName();
            Set<String> variablesReadInAssignment = extractVariablesFromExpression(assignment.getExpression());

            System.out.println("Assignment found: " + variableWritten +
                    " = " + assignment.getExpression());
            System.out.println("  Variable written: " + variableWritten);
            System.out.println("  Variable(s) read: " + variablesReadInAssignment);
        }
    }

}
