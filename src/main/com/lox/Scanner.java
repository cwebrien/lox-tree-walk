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

    private char getNextChar() {
        return source.charAt(current++);
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }
}
