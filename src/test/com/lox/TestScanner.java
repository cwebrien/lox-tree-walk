package com.lox;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestScanner {
    /**
     * Tests scanTokens() for one-character tokens.
     */
    @Test
    public void testScanTokensSingleChar() {
        // Try every strictly single-character token type
        List<TokenType> expectedTokens = List.of(
                TokenType.LEFT_PAREN,
                TokenType.RIGHT_PAREN,
                TokenType.LEFT_BRACE,
                TokenType.RIGHT_BRACE,
                TokenType.COMMA,
                TokenType.DOT,
                TokenType.MINUS,
                TokenType.PLUS,
                TokenType.SEMICOLON,
                TokenType.SLASH,
                TokenType.STAR,
                TokenType.EOF // there's always an implicit EOF
        );
        String ridiculousSource = "(){},.-+;/*";
        Scanner scanner = new Scanner(ridiculousSource);
        List<Token> scannedTokens = scanner.scanTokens();

        // TODO: check all contents of the token, not just the token type
        // TODO: in interim, replace with lambda
        List<TokenType> scannedTokenTypes = new ArrayList<TokenType>();
        for(Token t : scannedTokens) {
            scannedTokenTypes.add(t.getType());
        }
        Assert.assertEquals(expectedTokens, scannedTokenTypes);
    }

    /**
     * Tests scanTokens() for one-/two-character tokens.
     */
    @Test
    public void testScanTokensOneOrTwoChar() {
        // Try every strictly one/two-character token type
        List<TokenType> expectedTokens = List.of(
                TokenType.BANG,
                TokenType.LEFT_PAREN,
                TokenType.BANG_EQUAL,
                TokenType.LEFT_PAREN,
                TokenType.EQUAL,
                TokenType.LEFT_PAREN,
                TokenType.EQUAL_EQUAL,
                TokenType.LEFT_PAREN,
                TokenType.GREATER,
                TokenType.LEFT_PAREN,
                TokenType.GREATER_EQUAL,
                TokenType.LEFT_PAREN,
                TokenType.LESS,
                TokenType.LEFT_PAREN,
                TokenType.LESS_EQUAL,
                TokenType.EOF // there's always an implicit EOF
        );

        // Use '(' to delimit tokens
        String ridiculousSource = "!(!=(=(==(>(>=(<(<=";
        Scanner scanner = new Scanner(ridiculousSource);
        List<Token> scannedTokens = scanner.scanTokens();

        // TODO: check all contents of the token, not just the token type
        // TODO: in interim, replace with lambda
        List<TokenType> scannedTokenTypes = new ArrayList<TokenType>();
        for(Token t : scannedTokens) {
            scannedTokenTypes.add(t.getType());
        }
        Assert.assertEquals(expectedTokens, scannedTokenTypes);
    }

    /**
     * Tests scanTokens() for comments implementation and whitespace delimiting.
     */
    @Test
    public void testScanTokensComments() {
        List<TokenType> expectedTokens = List.of(
                TokenType.LEFT_PAREN,
                TokenType.SLASH,
                TokenType.RIGHT_PAREN,
                TokenType.LEFT_PAREN,
                TokenType.RIGHT_PAREN,
                TokenType.EOF // there's always an implicit EOF
        );
        String ridiculousSource = "(/\n)//lots of stuff after comment\n()";
        Scanner scanner = new Scanner(ridiculousSource);
        List<Token> scannedTokens = scanner.scanTokens();

        // TODO: check all contents of the token, not just the token type
        // TODO: in interim, replace with lambda
        List<TokenType> scannedTokenTypes = new ArrayList<TokenType>();
        for(Token t : scannedTokens) {
            scannedTokenTypes.add(t.getType());
        }
        Assert.assertEquals(expectedTokens, scannedTokenTypes);
    }

    @Test
    public void testScanTokensIdentifier() {
        List<TokenType> expectedTokens = List.of(
                TokenType.IDENTIFIER,
                TokenType.IDENTIFIER,
                TokenType.IDENTIFIER,
                TokenType.EOF // there's always an implicit EOF
        );
        String ridiculousSource = "foo bar baz";
        Scanner scanner = new Scanner(ridiculousSource);
        List<Token> scannedTokens = scanner.scanTokens();

        // TODO: check all contents of the token, not just the token type
        // TODO: in interim, replace with lambda
        List<TokenType> scannedTokenTypes = new ArrayList<TokenType>();
        for(Token t : scannedTokens) {
            scannedTokenTypes.add(t.getType());
        }
        Assert.assertEquals(expectedTokens, scannedTokenTypes);
    }
}


/*
    // Literals.
    IDENTIFIER,STRING,NUMBER,

    // Keywords.
    AND,CLASS,ELSE,FALSE,FUN,FOR,IF,NIL,OR,
    PRINT,RETURN,SUPER,THIS,TRUE,VAR,WHILE,

*/