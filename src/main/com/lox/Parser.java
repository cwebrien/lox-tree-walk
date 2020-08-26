package com.lox;

import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int currentIndex = 0;

    /**
     * Constructs a parser from an ordered list of tokens.
     * @param tokens Tokens which have been scanned
     */
    Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Our expression grammar:
     * expression     → equality ;
     * equality       → comparison ( ( "!=" | "==" ) comparison )* ;
     * comparison     → addition ( ( ">" | ">=" | "<" | "<=" ) addition )* ;
     * addition       → multiplication ( ( "-" | "+" ) multiplication )* ;
     * multiplication → unary ( ( "/" | "*" ) unary )* ;
     * unary          → ( "!" | "-" ) unary
     *                | primary ;
     * primary        → NUMBER | STRING | "false" | "true" | "nil"
     *                | "(" expression ")" ;
     */


    /**
     * Our expression rule simply moves down through the rule hierarchy to the next rule: equality().
     * @return The precedence rule expression
     */
    private Expression expression() {
        return equality();
    }

    /**
     * Handles the equality precedence rule.
     * @return The rule expression
     */
    private Expression equality() {
        Expression expr = null;
        return expr;
    }

    private Expression comparison() {
        Expression expr = null;
        return expr;
    }

    private Expression addition() {
        Expression expr = null;
        return expr;
    }

    private Expression multiplication() {
        Expression expr = null;
        return expr;
    }

    private Expression unary() {
        Expression expr = null;
        return expr;
    }

    private Expression primary() {
        Expression expr = null;
        return expr;
    }

    private Token advance() {
        Token token = tokens.get(currentIndex);
        currentIndex++;

        return token;
    }

    /**
     * Return the current token without advancing the token index.
     * @return The current token
     */
    private Token peek() {
        return tokens.get(currentIndex);
    }

    /**
     * Return the previously consumed token.
     * @return The previously consumed token
     */
    private Token previous() {
        return tokens.get(currentIndex - 1 );
    }

    /**
     * We're at the end if we explicitly see an EOF token, or if we have consumed all of the tokens in the list.
     * @return True if we've consumed all of the tokens up to the EOD; otherwise false
     */
    private boolean isAtEnd() {
        return currentIndex >= tokens.size() || peek().getType() == TokenType.EOF;
    }

    /**
     * Returns true and moves forward if the current token matches any of the types specified.
     * @param types A set of types for matching
     * @return True if and only if we find a match
     */
    private boolean matchAny(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for a match of the current token to the type specified.
     * @param type To check against
     * @return True if and only if the current token being processed is of the specified type
     */
    private boolean check(TokenType type) {
        if (isAtEnd()) {
            return false;
        }
        return peek().getType() == type;
    }
}