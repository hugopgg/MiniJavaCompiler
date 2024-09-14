package com.inf5153.ast.parser;

/**
 * The Token class represents a single token in the source code.
 * A token consists of a type and a value.
 */
public class Token {
    private TokenType type;
    private String value;

    /**
     * Constructs a new Token instance with the specified type and value.
     *
     * @param type  the type of the token
     * @param value the value of the token
     */
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Returns the type of this token.
     *
     * @return the type of the token
     */
    public TokenType getType() {
        return type;
    }

    /**
     * Returns the value of this token.
     *
     * @return the value of the token
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns a string representation of this token.
     * The string format is "Token-> type: [type], value: [value]",
     * or "EOF" if the token type is EOF.
     *
     * @return a string representation of the token
     */
    @Override
    public String toString() {
        if (type == TokenType.EOF) {
            return "EOF";
        }
        return String.format("Token-> type: %s, value: %s", type, value);
    }
}
