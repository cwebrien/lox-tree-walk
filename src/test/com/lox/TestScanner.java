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
        String ridiculousSource = "(/\n)//lots of stuff after comment\n()//another comment";
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
     * Test scanTokens() for identifiers.
     */
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

    /**
     * Test scanTokens() for reserved words and identifiers together.
     */    
    @Test
    public void testScanTokensReserved() {
        List<TokenType> expectedTokens = List.of(
                TokenType.FOR,
                TokenType.IDENTIFIER,
                TokenType.IF,
                TokenType.EOF // there's always an implicit EOF
        );
        String ridiculousSource = "for bar if";
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
     * Test scanTokens() for all possible reserved tokens.
     */    
    @Test
    public void testScanTokensAllReserved() {
        List<TokenType> expectedTokens = List.of(
                TokenType.AND,
                TokenType.CLASS,
                TokenType.ELSE,
                TokenType.FALSE,
                TokenType.FOR,
                TokenType.NIL,
                TokenType.IF,
                TokenType.OR,
                TokenType.FUN,
                TokenType.PRINT,
                TokenType.RETURN,
                TokenType.SUPER,
                TokenType.THIS,
                TokenType.TRUE,
                TokenType.VAR,
                TokenType.WHILE,
                TokenType.EOF
        );
        String ridiculousSource = "and class else false for nil if or fun print return super this true var while";
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
     * Tests scanTokens() for a bit of lox that iterates over a print statement.
     */
    @Test
    public void testScanTokensForLoop() {
        List<TokenType> expectedTokens = List.of(
                TokenType.FOR,
                TokenType.LEFT_PAREN,
                TokenType.VAR,
                TokenType.IDENTIFIER,
                TokenType.EQUAL,
                TokenType.NUMBER,
                TokenType.SEMICOLON,
                TokenType.IDENTIFIER,
                TokenType.LESS,
                TokenType.NUMBER,
                TokenType.SEMICOLON,
                TokenType.IDENTIFIER,
                TokenType.EQUAL,
                TokenType.IDENTIFIER,
                TokenType.PLUS,
                TokenType.NUMBER,
                TokenType.RIGHT_PAREN,
                TokenType.LEFT_BRACE,
                TokenType.PRINT,
                TokenType.IDENTIFIER,
                TokenType.SEMICOLON,
                TokenType.RIGHT_BRACE,
                TokenType.EOF // there's always an implicit EOF
        );
        String ridiculousSource = "for (var a = 1; a < 10; a = a + 1) {\n" +
                                  "\tprint a;\n" +
                                  "}";
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
     * Tests scanTokens() for string literals.
     */
    @Test
    public void testScanTokensStringLiteral() {
        List<TokenType> expectedTokens = List.of(
                TokenType.IF,
                TokenType.IDENTIFIER,
                TokenType.STRING,
                TokenType.IF,
                TokenType.EOF // there's always an implicit EOF
        );
        String ridiculousSource = "if some_identifier \"string literal 123\" if";
        Scanner scanner = new Scanner(ridiculousSource);
        List<Token> scannedTokens = scanner.scanTokens();

        // TODO: check all contents of the token, not just the token type
        // TODO: in interim, replace with lambda
        List<TokenType> scannedTokenTypes = new ArrayList<TokenType>();
        for (Token t : scannedTokens) {
            scannedTokenTypes.add(t.getType());
        }
        Assert.assertEquals(expectedTokens, scannedTokenTypes);
        Assert.assertEquals(scannedTokens.get(2).getLiteral(), "string literal 123");
    }

    /**
     * Tests peekNextChar().
     */
    @Test
    public void testPeekNextChar() {
        String source = "abcd123";
        Scanner scanner = new Scanner(source);
        Assert.assertEquals(scanner.peekNextChar(), 'a'); // implicitly reads next character
        Assert.assertEquals(scanner.peekNextChar(1), 'a'); // explicitly reads next character
        Assert.assertEquals(scanner.peekNextChar(3), 'c'); // reads 3rd next character
        Assert.assertEquals(scanner.peekNextChar(-1), '\0'); // negative is an invalid peek
        Assert.assertEquals(scanner.peekNextChar(7), '3'); // last possible peek
        Assert.assertEquals(scanner.peekNextChar(8), '\0'); // peeking past end of source
    }

    /**
     * Tests matchNextChar().
     */
    @Test
    public void testMatchNextChar() {
        String source = "abcd";
        Scanner scanner = new Scanner(source);
        Assert.assertTrue(scanner.matchNextChar('a')); // has side effect of advancing
        Assert.assertTrue(scanner.matchNextChar('b')); // same side effect
        Assert.assertFalse(scanner.matchNextChar('d')); // should not advance since it's a fail
        Assert.assertTrue(scanner.matchNextChar('c')); // advancing side effect
        Assert.assertFalse(scanner.matchNextChar('e')); // we're at EOF

    }
}
