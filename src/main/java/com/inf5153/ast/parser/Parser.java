package com.inf5153.ast.parser;

import com.inf5153.ast.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The Parser class is responsible for parsing a list of tokens and
 * constructing an Abstract Syntax Tree (AST) based on the language's
 * grammar rules.
 */
public class Parser {
    private final List<Token> tokens;
    private int current = 0;

    /**
     * Constructs a new Parser instance with the provided list of tokens.
     *
     * @param tokens the list of tokens to be parsed
     */
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Parses the list of tokens and returns the root of the Abstract Syntax Tree
     * (AST).
     *
     * @return the root Block node of the AST
     */
    public Block parse() {
        return parseBlock();
    }

    /**
     * Parses a block of code enclosed in curly braces.
     *
     * @return a Block node containing the statements within the block
     */
    private Block parseBlock() {
        List<Statement> statements = new ArrayList<>();

        consume(TokenType.PUNCTUATION, "{");

        while (!check(TokenType.PUNCTUATION, "}") && !isAtEnd()) {
            statements.add(parseStatement());
        }

        consume(TokenType.PUNCTUATION, "}");

        return new Block(statements);
    }

    /**
     * Parses a statement based on the token type.
     *
     * @return a Statement node representing the parsed statement
     */
    private Statement parseStatement() {
        if (match(TokenType.KEYWORD, "if")) {
            return parseIfStatement();
        }
        if (match(TokenType.KEYWORD, "while")) {
            return parseWhileStatement();
        }
        if (match(TokenType.KEYWORD, "print")) {
            return parsePrintStatement();
        }
        if (match(TokenType.KEYWORD, "read")) {
            return parseReadStatement();
        }
        return parseAssignment();
    }

    /**
     * Parses an 'if' statement with an optional 'else' block.
     *
     * @return an IfStatement node representing the parsed if statement
     */
    private IfStatement parseIfStatement() {
        Condition condition = parseCondition();
        consume(TokenType.KEYWORD, "then");
        Block thenBlock = parseBlock();
        Block elseBlock = null;
        if (match(TokenType.KEYWORD, "else")) {
            elseBlock = parseBlock();
        }
        return new IfStatement(condition, thenBlock, elseBlock);
    }

    /**
     * Parses a 'while' statement.
     *
     * @return a WhileStatement node representing the parsed while statement
     */
    private WhileStatement parseWhileStatement() {
        Condition condition = parseCondition();

        Block body = parseBlock();
        return new WhileStatement(condition, body);
    }

    /**
     * Parses a 'print' statement.
     *
     * @return a PrintStatement node representing the parsed print statement
     */
    private PrintStatement parsePrintStatement() {
        consume(TokenType.PUNCTUATION, "(");
        Expression expression = parseExpression();
        consume(TokenType.PUNCTUATION, ")");
        consume(TokenType.PUNCTUATION, ";");
        return new PrintStatement(expression);
    }

    /**
     * Parses a 'read' statement.
     *
     * @return a ReadStatement node representing the parsed read statement
     */
    private ReadStatement parseReadStatement() {
        LiteralString literalString = null;
        consume(TokenType.PUNCTUATION, "(");
        if (match(TokenType.STRINGLITERAL)) {
            literalString = new LiteralString(previous().getValue());
        }
        Identifier identifier = parseIdentifier();
        consume(TokenType.PUNCTUATION, ")");
        consume(TokenType.PUNCTUATION, ";");
        return new ReadStatement(literalString, identifier);
    }

    /**
     * Parses an assignment statement.
     *
     * @return an Assignment node representing the parsed assignment statement
     */
    private Assignment parseAssignment() {
        Identifier identifier = parseIdentifier();
        consume(TokenType.OPERATOR, "=");
        Expression value = parseExpression();
        consume(TokenType.PUNCTUATION, ";");
        return new Assignment(identifier, value);
    }

    /**
     * Parses a condition expression within parentheses.
     *
     * @return a Condition node representing the parsed condition expression
     */
    private Condition parseCondition() {
        consume(TokenType.PUNCTUATION, "(");
        Expression left = parseExpression();
        Token operator = consume(TokenType.OPERATOR);
        Expression right = parseExpression();
        consume(TokenType.PUNCTUATION, ")");
        return new Condition(left, operator.getValue(), right);
    }

    /**
     * Parses an expression, which can include binary operations.
     *
     * @return an Expression node representing the parsed expression
     */
    private Expression parseExpression() {
        return parseBinaryExpression();
    }

    /**
     * Parses a binary expression, handling operators like +, -, *, and /.
     *
     * @return an Expression node representing the parsed binary expression
     */
    private Expression parseBinaryExpression() {
        Expression left = parseUnaryExpression();
        while (match(TokenType.OPERATOR, "+") || match(TokenType.OPERATOR, "-") ||
                match(TokenType.OPERATOR, "*") || match(TokenType.OPERATOR, "/")) {
            Token operator = previous();
            Expression right = parseUnaryExpression();
            left = new BinaryExpression(left, operator.getValue(), right);
        }
        return left;
    }

    /**
     * Parses a unary expression, handling unary operators like -.
     *
     * @return an Expression node representing the parsed unary expression
     */
    private Expression parseUnaryExpression() {
        if (match(TokenType.OPERATOR, "-")) {
            Token operator = previous();
            Expression right = parsePrimary();
            return new UnaryExpression(right, operator.getValue());
        }
        return parsePrimary();
    }

    /**
     * Parses a primary expression, which can be a number, string literal, or
     * identifier.
     *
     * @return an Expression node representing the parsed primary expression
     * @throws RuntimeException if an expected expression is not found
     */
    private Expression parsePrimary() {
        if (match(TokenType.PUNCTUATION, "(")) {
            Expression expr = parseExpression();
            consume(TokenType.PUNCTUATION, ")");
            return expr;
        }
        if (match(TokenType.NUMBER)) {
            return new LiteralNumber(Integer.parseInt(previous().getValue()));
        }
        if (match(TokenType.STRINGLITERAL)) {
            // Remove the surrounding quotes
            String value = previous().getValue();
            value = value.substring(1, value.length() - 1);
            return new LiteralString(value);
        }
        if (match(TokenType.IDENTIFIER)) {
            return new Identifier(previous().getValue());
        }
        throw new RuntimeException("Expected expression.");
    }

    /**
     * Parses an identifier token.
     *
     * @return an Identifier node representing the parsed identifier
     * @throws RuntimeException if an identifier is not found
     */
    private Identifier parseIdentifier() {
        if (match(TokenType.IDENTIFIER)) {
            return new Identifier(previous().getValue());
        }
        throw new RuntimeException("Expected identifier.");
    }

    /**
     * Checks if the next token matches the specified type and advances the token
     * stream.
     *
     * @param type the type of token to match
     * @return true if the token matches the type, false otherwise
     */
    private boolean match(TokenType type) {
        if (check(type)) {
            advance();
            return true;
        }
        return false;
    }

    /**
     * Checks if the next token matches the specified type and value, and advances
     * the token stream.
     *
     * @param type  the type of token to match
     * @param value the value of the token to match
     * @return true if the token matches the type and value, false otherwise
     */
    private boolean match(TokenType type, String value) {
        if (check(type, value)) {
            advance();
            return true;
        }
        return false;
    }

    /**
     * Consumes the next token of the specified type and returns it.
     *
     * @param type the type of token to consume
     * @return the consumed token
     * @throws RuntimeException if the token of the specified type is not found
     */
    private Token consume(TokenType type) {
        if (check(type)) {
            return advance();
        }
        throw new RuntimeException("Expected " + type + " but found " + peek().getType());
    }

    /**
     * Consumes the next token of the specified type and value, and returns it.
     *
     * @param type  the type of token to consume
     * @param value the value of the token to consume
     * @return the consumed token
     * @throws RuntimeException if the token of the specified type and value is not
     *                          found
     */
    private Token consume(TokenType type, String value) {
        if (check(type, value)) {
            return advance();
        }
        throw new RuntimeException("Expected '" + value + "' but found " + peek().getValue());
    }

    /**
     * Checks if the next token matches the specified type.
     *
     * @param type the type of token to check
     * @return true if the token matches the type, false otherwise
     */
    private boolean check(TokenType type) {
        if (isAtEnd()) {
            return false;
        }
        return peek().getType() == type;
    }

    /**
     * Checks if the next token matches the specified type and value.
     *
     * @param type  the type of token to check
     * @param value the value of the token to check
     * @return true if the token matches the type and value, false otherwise
     */
    private boolean check(TokenType type, String value) {
        if (isAtEnd()) {
            return false;
        }
        Token token = peek();
        return token.getType() == type && token.getValue().equals(value);
    }

    /**
     * Advances the token stream by one and returns the previous token.
     *
     * @return the previous token
     */
    private Token advance() {
        if (!isAtEnd()) {
            current++;
        }
        return previous();
    }

    /**
     * Checks if the token stream has reached the end.
     *
     * @return true if the token stream is at the end, false otherwise
     */
    private boolean isAtEnd() {
        return peek().getType() == TokenType.EOF;
    }

    /**
     * Retrieves the next token in the token stream without advancing.
     *
     * @return the next token
     */
    private Token peek() {
        return tokens.get(current);
    }

    /**
     * Retrieves the previous token in the token stream.
     *
     * @return the previous token
     */
    private Token previous() {
        return tokens.get(current - 1);
    }
}
