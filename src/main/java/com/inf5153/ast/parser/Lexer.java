package com.inf5153.ast.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The Lexer class is responsible for tokenizing an input string.
 * It uses regular expressions to identify and categorize tokens.
 */
public class Lexer {
    private static final Pattern TOKEN_PATTERN = Pattern.compile(
            "\\s*(?<KEYWORD>if|then|else|while|print|read)|" + // Capture keywords first
                    "\\s*(?<NUMBER>[0-9]+)|" + // Capture numbers
                    "\\s*(?<STRINGLITERAL>\"([^\"]*)\")|" + // Capture string literals without quotes
                    "\\s*(?<OPERATOR>(==|!=|<=|>=|[+\\-*/<=>!]))|" + // Capture operators
                    "\\s*(?<PUNCTUATION>[;(){}])|" + // Capture punctuation
                    "\\s*(?<IDENTIFIER>[a-zA-Z]+)" // Capture identifiers last
    );

    private Matcher matcher;

    /**
     * Constructs a new Lexer instance with the provided input string.
     *
     * @param input the input string to be tokenized
     */
    public Lexer(String input) {
        matcher = TOKEN_PATTERN.matcher(input);
    }

    /**
     * Tokenizes the input string and returns a list of tokens.
     *
     * @return a list of tokens extracted from the input string
     */
    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (matcher.find()) {
            if (matcher.group("KEYWORD") != null) {
                tokens.add(new Token(TokenType.KEYWORD, matcher.group("KEYWORD")));
            } else if (matcher.group("NUMBER") != null) {
                tokens.add(new Token(TokenType.NUMBER, matcher.group("NUMBER")));
            } else if (matcher.group("STRINGLITERAL") != null) {
                tokens.add(new Token(TokenType.STRINGLITERAL, matcher.group("STRINGLITERAL")));
            } else if (matcher.group("OPERATOR") != null) {
                tokens.add(new Token(TokenType.OPERATOR, matcher.group("OPERATOR")));
            } else if (matcher.group("PUNCTUATION") != null) {
                tokens.add(new Token(TokenType.PUNCTUATION, matcher.group("PUNCTUATION")));
            } else if (matcher.group("IDENTIFIER") != null) {
                tokens.add(new Token(TokenType.IDENTIFIER, matcher.group("IDENTIFIER")));
            }
        }
        tokens.add(new Token(TokenType.EOF, "")); // Add the EOF token

        return tokens;
    }
}
