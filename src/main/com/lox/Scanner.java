package com.lox;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private int startChar = 0;
    private int currentChar = 0;
    private int line = 1;

    /**
     * Create a scanner tied to some source code. This scanner is stateful when it scans.
     * @param source The source code to scan
     */
    public Scanner(String source) {
        this.source = source;
    }

    /**
     * Lex the source code and accumulate tokens. This has the side effect of moving through all of the source code.
     * @return The lexed tokens
     */
    public List<Token> scanTokens() {
        while (!isAtEnd()) {
            // Update the starting character for the next token
            startChar = currentChar;

            // Process the token and add it
            Token t = getNextToken();
            if(t != null) {
                tokens.add(t);
            }
        }

        // Wrap with an end-of-file token for good form
        tokens.add(buildToken(TokenType.EOF));
        return tokens;
    }

    /**
     * Pulls one or more characters to lex the next token.
     * @return
     */
    private Token getNextToken() {
        char c = pullNextChar();
        Token token = null;

        // Handle everything but string and numeric literals
        switch(c) {
            // Single-character tokens
            case '(': token = buildToken(TokenType.LEFT_PAREN); break;
            case ')': token = buildToken(TokenType.RIGHT_PAREN); break;
            case '{': token = buildToken(TokenType.LEFT_BRACE); break;
            case '}': token = buildToken(TokenType.RIGHT_BRACE); break;
            case ',': token = buildToken(TokenType.COMMA); break;
            case '.': token = buildToken(TokenType.DOT); break;
            case '-': token = buildToken(TokenType.MINUS); break;
            case '+': token = buildToken(TokenType.PLUS); break;
            case ';': token = buildToken(TokenType.SEMICOLON); break;
            case '*': token = buildToken(TokenType.STAR); break;

            // Double-character tokens (potentially)
            case '!': token = buildToken(matchNextChar('=') ? TokenType.BANG_EQUAL : TokenType.BANG); break;
            case '=': token = buildToken(matchNextChar('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL); break;
            case '<': token = buildToken(matchNextChar('=') ? TokenType.LESS_EQUAL : TokenType.LESS); break;
            case '>': token = buildToken(matchNextChar('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER); break;

            // Possibly a comment
            case '/':
                if(matchNextChar('/')) {
                    // A comment goes until the end of the line, so shamelessly rip characters
                    while (peekNextChar() != '\n' && !isAtEnd()) {
                        pullNextChar();
                    }
                } else {
                    token = buildToken(TokenType.SLASH);
                }
                break;

            // Whitespace
            case ' ':
            case '\r':
            case '\t':
                // Ignore whitespace.
                break;

            // Newline
            case '\n':
                line++;
                break;

            // String literals
            case '"':
                String literal = processString();
                token = buildToken(TokenType.STRING, literal);

            default:
                Lox.error(line, "Unexpected character.");
        }

        return token;
    }

    /**
     * Builds up a token that contains a literal (which happen to be string or numeric).
     * @param type The token type
     * @param literal The literal associated with the token
     * @return An inflated token
     */
    private Token buildToken(TokenType type, Object literal) {
        String lexeme = source.substring(startChar, currentChar);
        return new Token(type, lexeme, literal, line);
    }

    /**
     * Builds up a token of a given type.
     * @param type The token type
     * @return An inflated token
     */
    private Token buildToken(TokenType type) {
        return buildToken(type, null);
    }

    /**
     * Consume the next character only if it matches expected. Like a conditional advance().
     * @param expected The character that we're looking for
     * @return If we found expected in the next character
     */
    private boolean matchNextChar(char expected) {
        if (isAtEnd() || source.charAt(currentChar) != expected) {
            return false;
        }

        currentChar++;
        return true;
    }

    /**
     * Looks forward one character.
     * @return The next character or the null-terminating character if we're done reading.
     */
    private char peekNextChar() {
        if (isAtEnd()) {
            return '\0';
        }
        return source.charAt(currentChar);
    }

    /**
     * Pulls the next character and moves us forward in the buffer.
     * @return The next character (with side effects)
     */
    private char pullNextChar() {
        return source.charAt(currentChar++);
    }

    /**
     * We've completed reading the source code buffer.
     * @return True if we've consumed all of the source code
     */
    private boolean isAtEnd() {
        return currentChar >= source.length();
    }

    /**
     * Process a string literal.
     */
    private String processString() {
        String literal = null;

        // Keep pulling until we hit the terminating quote
        while(!isAtEnd() && peekNextChar() != '"') {
            pullNextChar();
        }

        // Did we hit a terminating quote or the end of the source buffer?
        if(isAtEnd()) {
            Lox.error(line, "Unterminated string literal");
        }
        else {
            // There's the terminating quote to pull
            pullNextChar();

            literal = source.substring(startChar + 1, currentChar - 1);
        }
        return literal;
    }
}
