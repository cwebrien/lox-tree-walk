package com.lox;

import java.util.List;

class Parser {
    private final List<Token> tokens;
    private int currentToken = 0;

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
}