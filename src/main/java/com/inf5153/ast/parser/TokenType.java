package com.inf5153.ast.parser;

/**
 * The TokenType enum represents the different types of tokens that can be identified in the source code.
 * Each type corresponds to a specific category of tokens, such as numbers, keywords, or operators.
 */
public enum TokenType {
    /**
     * Represents a numeric literal.
     */
    NUMBER,

    /**
     * Represents an identifier.
     */
    IDENTIFIER,

    /**
     * Represents a string literal.
     */
    STRINGLITERAL,

    /**
     * Represents operators used in expressions.
     */
    OPERATOR,

    /**
     * Represents punctuation characters.
     */
    PUNCTUATION,

    /**
     * Represents reserved keywords in the language, such as if, while, print, etc.
     */
    KEYWORD,

    /**
     * Represents the end of the input source code.
     */
    EOF
}
