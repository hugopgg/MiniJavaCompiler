package com.inf5153.ast.visitor;

import com.inf5153.ast.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The ASTCodeExecutor class implements the Visitor pattern to executes the code
 * (Java style)
 * represented by an Abstract Syntax Tree.
 */
public class ASTCodeExecutor implements Visitor {
    private final Map<String, Integer> variables = new HashMap<>();
    private final Map<String, String> stringVariables = new HashMap<>();

    /**
     * Executes an assignment statement by storing the value of an expression in a
     * variable.
     *
     * @param assignment The assignment statement to execute.
     */
    @Override
    public void visit(Assignment assignment) {
        String identifier = assignment.getIdentifier().getName();
        if (assignment.getExpression() instanceof LiteralString) {
            String value = ((LiteralString) assignment.getExpression()).getValue();
            stringVariables.put(identifier, value);
        } else {
            int value = evaluateExpression(assignment.getExpression());
            variables.put(identifier, value);
        }
    }

    /**
     * Executes a print statement by printing the evaluated value of an expression.
     *
     * @param printStatement The print statement to execute.
     */
    @Override
    public void visit(PrintStatement printStatement) {
        Expression ex = printStatement.getExpression();
        String name = ((Identifier) ex).getName();
        if (stringVariables.containsKey(name)) {
            System.out.println(stringVariables.get(name));
            return;
        }
        int value = evaluateExpression(printStatement.getExpression());
        System.out.println(value);
    }

    /**
     * Executes a read statement by reading an integer value from standard input and
     * assigning it to the identifier.
     *
     * @param readStatement The read statement to execute.
     */
    @Override
    public void visit(ReadStatement readStatement) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        if (readStatement.getLiteralString() != null) {
            System.out.print(readStatement.getLiteralString().getValue() + " ");
        }
        int value = scanner.nextInt();
        String identifier = readStatement.getIdentifier().getName();
        variables.put(identifier, value);

    }

    /**
     * Executes an if statement by evaluating the condition and executing the
     * appropriate block.
     *
     * @param ifStatement The if statement to execute.
     */
    @Override
    public void visit(IfStatement ifStatement) {
        boolean conditionResult = evaluateCondition(ifStatement.getCondition());
        if (conditionResult) {
            ifStatement.getThenBlock().accept(this);
        } else if (ifStatement.getElseBlock() != null) {
            ifStatement.getElseBlock().accept(this);
        }
    }

    /**
     * Executes a while statement by repeatedly executing the block while the
     * condition is true.
     *
     * @param whileStatement The while statement to execute.
     */
    @Override
    public void visit(WhileStatement whileStatement) {
        while (evaluateCondition(whileStatement.getCondition())) {
            whileStatement.getBlock().accept(this);
        }
    }

    /**
     * Executes a block by executing all contained statements sequentially.
     *
     * @param block The block to execute.
     */
    @Override
    public void visit(Block block) {
        for (Statement statement : block.getStatements()) {
            statement.accept(this);
        }
    }

    /**
     * Evaluates an expression and returns its integer value.
     *
     * @param expression The expression to evaluate.
     * @return The evaluated value.
     */
    private int evaluateExpression(Expression expression) {
        if (expression instanceof LiteralNumber) {
            return ((LiteralNumber) expression).getValue();
        } else if (expression instanceof Identifier) {
            String name = ((Identifier) expression).getName();
            if (!variables.containsKey(name)) {
                throw new RuntimeException("Error: Undefined variable - " + name);
            }
            return variables.get(name);
        } else if (expression instanceof UnaryExpression) {
            UnaryExpression unaryExpression = (UnaryExpression) expression;
            return evaluateUnaryExpression(unaryExpression);
        } else if (expression instanceof BinaryExpression) {
            BinaryExpression binaryExpression = (BinaryExpression) expression;
            return evaluateBinaryExpression(binaryExpression);
        }
        throw new RuntimeException("Unknown expression type");
    }

    /**
     * Evaluates a unary expression and returns its integer value.
     *
     * @param unaryExpression The unary expression to evaluate.
     * @return The evaluated value.
     */
    private int evaluateUnaryExpression(UnaryExpression unaryExpression) {
        int value = evaluateExpression(unaryExpression.getExpression());
        switch (unaryExpression.getOperator()) {
            case "-":
                return -value;
            default:
                throw new RuntimeException("Unsupported unary operator: " + unaryExpression.getOperator());
        }
    }

    /**
     * Evaluates a binary expression and returns its integer value.
     *
     * @param binaryExpression The binary expression to evaluate.
     * @return The evaluated value.
     */
    private int evaluateBinaryExpression(BinaryExpression binaryExpression) {
        int leftValue = evaluateExpression(binaryExpression.getLeft());
        int rightValue = evaluateExpression(binaryExpression.getRight());
        switch (binaryExpression.getOperator()) {
            case "+":
                return leftValue + rightValue;
            case "-":
                return leftValue - rightValue;
            case "*":
                return leftValue * rightValue;
            case "/":
                if (rightValue == 0) {
                    throw new RuntimeException("Division by zero!");
                }
                return leftValue / rightValue;
            default:
                throw new RuntimeException("Unsupported operator: " + binaryExpression.getOperator());
        }
    }

    /**
     * Evaluates a condition and returns whether it is true or false.
     *
     * @param condition The condition to evaluate.
     * @return true if the condition is met, otherwise false.
     */
    private boolean evaluateCondition(Condition condition) {
        int leftValue = evaluateExpression(condition.getLeft());
        int rightValue = evaluateExpression(condition.getRight());
        switch (condition.getOperator()) {
            case "==":
                return leftValue == rightValue;
            case "!=":
                return leftValue != rightValue;
            case "<":
                return leftValue < rightValue;
            case "<=":
                return leftValue <= rightValue;
            case ">":
                return leftValue > rightValue;
            case ">=":
                return leftValue >= rightValue;
            default:
                throw new RuntimeException("Unsupported condition operator: " + condition.getOperator());
        }
    }

    // Unused visit methods

    /**
     * Handles an identifier node. This method currently checks if the variable is
     * defined.
     *
     * @param identifier The identifier to handle.
     */
    @Override
    public void visit(Identifier identifier) {

    }

    /**
     * Handles a unary expression. This method is currently unused.
     *
     * @param unaryExpression The unary expression to handle.
     */
    @Override
    public void visit(UnaryExpression unaryExpression) {

    }

    /**
     * Handles a binary expression. This method is currently unused.
     *
     * @param binaryExpression The binary expression to handle.
     */
    @Override
    public void visit(BinaryExpression binaryExpression) {

    }

    /**
     * Handles a condition node. This method is currently unused.
     *
     * @param condition The condition to handle.
     */
    @Override
    public void visit(Condition condition) {

    }

    /**
     * Handles a literal number node. This method is currently unused.
     *
     * @param literalNumber The literal number to handle.
     */
    @Override
    public void visit(LiteralNumber literalNumber) {

    }

    /**
     * Handles a literal string node. This method is currently unused.
     *
     * @param literalString The literal string to handle.
     */
    @Override
    public void visit(LiteralString literalString) {

    }
}
