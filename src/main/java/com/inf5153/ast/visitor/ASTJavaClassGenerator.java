package com.inf5153.ast.visitor;

import com.inf5153.ast.*;
import com.inf5153.utils.FileUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The ASTJavaClassGenerator class implements the Visitor pattern to generate
 * Java source code from an Abstract Syntax Tree (AST).
 * It translates the AST nodes into Java code and writes the code to a file.
 */
public class ASTJavaClassGenerator implements Visitor {

    private StringBuilder javaCode = new StringBuilder(); // StringBuilder to accumulate Java code
    private int indentationLevel = 2; // Current indentation level for formatting
    private String className; // Name of the generated Java class
    private static final String PACKAGE_NAME = "com.inf5153.miniLang.javaGeneration"; // Package name
    private static final String BASE_PATH = "src/main/java"; // Base directory for saving the generated file

    /**
     * Constructs an ASTJavaClassGenerator with the specified class name.
     *
     * @param className the name of the Java class to generate
     */
    public ASTJavaClassGenerator(String className) {
        this.className = className;
    }

    /**
     * Visits an Assignment node and appends the corresponding Java code.
     *
     * @param assignment the Assignment node to visit
     */
    @Override
    public void visit(Assignment assignment) {
        addIndentation();
        javaCode.append(assignment.getIdentifier().getName()).append(" = ");
        assignment.getExpression().accept(this);
        javaCode.append(";\n");
    }

    /**
     * Visits a BinaryExpression node and appends the corresponding Java code.
     *
     * @param binaryExpression the BinaryExpression node to visit
     */
    @Override
    public void visit(BinaryExpression binaryExpression) {
        javaCode.append("(");
        binaryExpression.getLeft().accept(this);
        javaCode.append(" ").append(binaryExpression.getOperator()).append(" ");
        binaryExpression.getRight().accept(this);
        javaCode.append(")");
    }

    /**
     * Visits a Block node and appends the corresponding Java code.
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
     * Visits a Condition node and appends the corresponding Java code.
     *
     * @param condition the Condition node to visit
     */
    @Override
    public void visit(Condition condition) {
        javaCode.append("(");
        condition.getLeft().accept(this);
        javaCode.append(" ").append(condition.getOperator()).append(" ");
        condition.getRight().accept(this);
        javaCode.append(")");
    }

    /**
     * Visits an Identifier node and appends the corresponding Java code.
     *
     * @param identifier the Identifier node to visit
     */
    @Override
    public void visit(Identifier identifier) {
        javaCode.append(identifier.getName());
    }

    /**
     * Visits an IfStatement node and appends the corresponding Java code.
     *
     * @param ifStatement the IfStatement node to visit
     */
    @Override
    public void visit(IfStatement ifStatement) {
        addIndentation();
        javaCode.append("if ");
        ifStatement.getCondition().accept(this);
        javaCode.append(" {\n");
        indentationLevel++;
        ifStatement.getThenBlock().accept(this);
        indentationLevel--;
        addIndentation();
        javaCode.append("}\n");
        if (ifStatement.getElseBlock() != null) {
            addIndentation();
            javaCode.append("else {\n");
            indentationLevel++;
            ifStatement.getElseBlock().accept(this);
            indentationLevel--;
            addIndentation();
            javaCode.append("}\n");
        }
    }

    /**
     * Visits a LiteralNumber node and appends the corresponding Java code.
     *
     * @param literalNumber the LiteralNumber node to visit
     */
    @Override
    public void visit(LiteralNumber literalNumber) {
        javaCode.append(literalNumber.getValue());
    }

    /**
     * Visits a LiteralString node and appends the corresponding Java code.
     *
     * @param literalString the LiteralString node to visit
     */
    @Override
    public void visit(LiteralString literalString) {
        javaCode.append("\"").append(literalString.getValue()).append("\"");
    }

    /**
     * Visits a PrintStatement node and appends the corresponding Java code.
     *
     * @param printStatement the PrintStatement node to visit
     */
    @Override
    public void visit(PrintStatement printStatement) {
        addIndentation();
        javaCode.append("System.out.println(");
        printStatement.getExpression().accept(this);
        javaCode.append(");\n");
    }

    /**
     * Visits a ReadStatement node and throws an UnsupportedOperationException as
     * ReadStatement is not supported.
     *
     * @param readStatement the ReadStatement node to visit
     */
    @Override
    public void visit(ReadStatement readStatement) {
        throw new UnsupportedOperationException("ReadStatement is not supported.");
    }

    /**
     * Visits a UnaryExpression node and appends the corresponding Java code.
     *
     * @param unaryExpression the UnaryExpression node to visit
     */
    @Override
    public void visit(UnaryExpression unaryExpression) {
        javaCode.append("(").append(unaryExpression.getOperator());
        unaryExpression.getExpression().accept(this);
        javaCode.append(")");
    }

    /**
     * Visits a WhileStatement node and appends the corresponding Java code.
     *
     * @param whileStatement the WhileStatement node to visit
     */
    @Override
    public void visit(WhileStatement whileStatement) {
        addIndentation();
        javaCode.append("while ");
        whileStatement.getCondition().accept(this);
        javaCode.append(" {\n");
        indentationLevel++;
        whileStatement.getBlock().accept(this);
        indentationLevel--;
        addIndentation();
        javaCode.append("}\n");
    }

    /**
     * Generates the Java class file based on the provided root Block.
     * This includes adding variable declarations and writing the generated code to
     * a file.
     *
     * @param rootBlock the root Block of the AST
     * @throws IOException if an I/O error occurs while writing the file
     */
    public void generateJavaClass(Block rootBlock) throws IOException {
        ASTAssignmentsCollector assignmentsGenerator = new ASTAssignmentsCollector();
        rootBlock.accept(assignmentsGenerator);
        List<Assignment> assignments = new ArrayList<>();
        assignments = assignmentsGenerator.getAssignments();
        addHeader();
        addVariableDeclarations(assignments);
        rootBlock.accept(this);
        javaCode.append("    }\n");
        javaCode.append("}\n");
        String directoryPath = BASE_PATH + "/" + PACKAGE_NAME.replace('.', '/');
        String filePath = directoryPath + "/" + className + ".java";
        FileUtils.writeToFile(filePath, javaCode.toString());
        System.out.println("Java class generated at: " + filePath);
    }

    /**
     * Adds a header to the generated Java code, including the package declaration
     * and main method.
     */
    private void addHeader() {
        javaCode.append("package ").append(PACKAGE_NAME).append(";\n\n");
        javaCode.append("public class ").append(className).append(" {\n");
        javaCode.append("    public static void main(String[] args) {\n");
    }

    /**
     * Adds variable declarations to the generated Java code based on the provided
     * list of assignments.
     *
     * @param assignments the list of assignments to generate variable declarations
     */
    private void addVariableDeclarations(List<Assignment> assignments) {
        Set<String> declaredVariables = new HashSet<>();
        for (Assignment assignment : assignments) {
            boolean isString = assignment.getExpression() instanceof LiteralString;
            String varName = assignment.getIdentifier().getName();
            if (!declaredVariables.contains(varName)) {
                addIndentation();
                javaCode.append(isString ? "String" : "int").append(" ").append(varName).append(";\n");
                declaredVariables.add(varName);
            }
        }
    }

    /**
     * Adds indentation to the generated Java code based on the current indentation
     * level.
     */
    private void addIndentation() {
        for (int i = 0; i < indentationLevel; i++) {
            javaCode.append("    ");
        }
    }

}
