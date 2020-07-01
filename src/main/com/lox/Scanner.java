package com.lox;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private int start = 0;
    private int current = 0;
    private int line = 1;

    /**
     * Create a scanner tied to some source code. This scanner is stateful when it scans.
     * @param source The source code to scan
     */
    public Scanner(String source) {
        this.source = source;
    }

    public List<Token> scanTokens() {
        while (!isAtEnd()) {
            Token t = getNextToken();
            if(t != null) {
                tokens.add(t);
            }
        }
        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    private Token getNextToken() {
        char c = getNextChar();
        TokenType tokenType = null;
        switch (c) {
            case '(': tokenType = TokenType.LEFT_PAREN; break;
            case ')': tokenType = TokenType.RIGHT_PAREN; break;
            case '{': tokenType = TokenType.LEFT_BRACE; break;
            case '}': tokenType = TokenType.RIGHT_BRACE; break;
            case ',': tokenType = TokenType.COMMA; break;
            case '.': tokenType = TokenType.DOT; break;
            case '-': tokenType = TokenType.MINUS; break;
            case '+': tokenType = TokenType.PLUS; break;
            case ';': tokenType = TokenType.SEMICOLON; break;
            case '*': tokenType = TokenType.STAR; break;
            case '!': tokenType = (match('=') ? TokenType.BANG_EQUAL : TokenType.BANG); break;
            case '=': tokenType = (match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL); break;
            case '<': tokenType = (match('=') ? TokenType.LESS_EQUAL : TokenType.LESS); break;
            case '>': tokenType = (match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER); break;
        }

        if(tokenType == null) {
            Lox.error(line, "Unexpected character.");
            return null;
        }
        else {
            String text = source.substring(start, current);
            return new Token(tokenType, text, null, line);
        }
    }

    /**
     * Consume the next character only if it matches expected. Like a conditional advance().
     * @param expected The character that we're looking for
     * @return If we found expected in the next character
     */
    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;

        current++;
        return true;
    }

    private char getNextChar() {
        return source.charAt(current++);
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }
}
