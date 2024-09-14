package com.inf5153;

import com.inf5153.ast.*;
import com.inf5153.ast.parser.Lexer;
import com.inf5153.ast.parser.Parser;
import com.inf5153.ast.parser.Token;
import com.inf5153.ast.visitor.ASTAssignmentsCollector;
import com.inf5153.ast.visitor.ASTCodeExecutor;
import com.inf5153.ast.visitor.ASTCodePrinter;
import com.inf5153.ast.visitor.ASTTreeMaker;
import com.inf5153.ast.visitor.ASTJavaClassGenerator;
import com.inf5153.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The main entry point.
 */
public class App {

    public static void main(String[] args) {
        String directoryPath = "testFiles";
        System.out.println("Running tests on test files in directory: " + directoryPath);
        runTests(directoryPath);
    }

    /**
     * Run tests in directoryPath.
     *
     * @param directoryPath the path of the directory containing the test files
     */
    private static void runTests(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("Invalid directory: " + directoryPath);
            return;
        }

        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.err.println("No test files found in directory: " + directoryPath);
            return;
        }

        for (File file : files) {
            System.out.println("\n===> Testing file: " + file.getName() + " <===\n");
            testWithFile(file.getPath());
        }
    }

    /**
     * Test: Tokens, Parsing, Basic code printer, AST tree maker, Code executor,
     * Java class generator, Assignments collector
     *
     * @param filePath the path of the file containing the source code to be tested
     */
    private static void testWithFile(String filePath) {
        try {
            String code = FileUtils.readFile(filePath);

            Lexer lexer = new Lexer(code);
            List<Token> tokens = lexer.tokenize();

            System.out.println("GENERATED TOKENS:");
            for (Token token : tokens) {
                System.out.println(token);
            }

            Parser parser = new Parser(tokens);
            Block ast = parser.parse();

            System.out.println("\nGENERATED CODE:");
            ASTCodePrinter printer = new ASTCodePrinter();
            ast.accept(printer);

            System.out.println("\nGENERATED AST TREE:");
            ASTTreeMaker treeMaker = new ASTTreeMaker();
            ast.accept(treeMaker);
            treeMaker.printTree();

            System.out.println("\nEXECUTING CODE:");
            ASTCodeExecutor executor = new ASTCodeExecutor();
            ast.accept(executor);

            System.out.println("\nGENERATING JAVA CLASS:");
            String className = FileUtils.generateClassName(filePath);
            ASTJavaClassGenerator javaGenerator = new ASTJavaClassGenerator(className);
            javaGenerator.generateJavaClass(ast);

            System.out.println("\nCOLLECTING ASSIGNMENTS:");
            ASTAssignmentsCollector assignmentsCollector = new ASTAssignmentsCollector();
            ast.accept(assignmentsCollector);
            assignmentsCollector.printAssignments();

        } catch (IOException e) {
            System.err.println("File read error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Parsing error: " + e.getMessage());
        }
    }
}
