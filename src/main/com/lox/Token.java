package com.lox;


public class Token {
    private final TokenType type;
    private final String lexeme;
    private final Object literal;
    private final int line;

    /**
     * A token is defined by its type, underlying data and its location in the execution script or interactive session.
     * @param type Token type
     * @param lexeme The actual string value of the lexeme
     * @param literal If it's a literal, we might as well inflate it now
     * @param line Line location of the lexeme
     */
    public Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    /**
     * Provides the type of token.
     * @return The type of token
     */
    public TokenType getType() {
        return this.type;
    }

    /**
     * Stringification of the token.
     * @return A string representation.
     */
    public String toString() {
        return type + " " + lexeme + " " + literal + " " + line;
    }
}
