package com.lox;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestScanner {
    /**
     *
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
        String ridiculousSource = "(){},.-+;*";
        Scanner scanner = new Scanner(ridiculousSource);
        List<Token> scannedTokens = scanner.scanTokens();

        Assert.assertEquals(expectedTokens, scannedTokens);
    }

/*
    LEFT_PAREN,RIGHT_PAREN,LEFT_BRACE,RIGHT_BRACE,
    COMMA,DOT,MINUS,PLUS,SEMICOLON,SLASH,STAR,

    // One or two character tokens.
    BANG,BANG_EQUAL,
    EQUAL,EQUAL_EQUAL,
    GREATER,GREATER_EQUAL,
    LESS,LESS_EQUAL,
    COMMENT,

    // Literals.
    IDENTIFIER,STRING,NUMBER,

    // Keywords.
    AND,CLASS,ELSE,FALSE,FUN,FOR,IF,NIL,OR,
    PRINT,RETURN,SUPER,THIS,TRUE,VAR,WHILE,

    EOF;

    assertTrue("app should have a greeting",true);*/
}
