package com.lox;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestAstPrinter {
    // -3.14 * (2.718) --> (* (- 3.14) (grouping 2.718))
    static final Expression TEST_EXPRESSION = new Expression.Binary(
            new Expression.Unary(
                    new Token(TokenType.MINUS, "-", null, 1),
                    new Expression.Literal(3.14)),
            new Token(TokenType.STAR, "*", null, 1),
            new Expression.Grouping(
                    new Expression.Literal(2.718)));

    /**
     * Test expressionAsString()
     */
    @Test
    public void testExpressionAsString() {
        AstPrinter printer = new AstPrinter();
        String result = printer.expressionAsString(TEST_EXPRESSION);
        assertEquals("(* (- 3.14) (grouping 2.718))", result);
    }

    /**
     * Test isUseful().
     */
    @Test
    public void testIsUseful() {
        // Comments are not useful; everything else is
        TokenType[] allTypes = TokenType.class.getEnumConstants();
        for(TokenType t : allTypes) {
            if(t == TokenType.COMMENT) {
                assertFalse(TokenType.isUseful(new Token(t, "", null, -1)));
            }
            else {
                assertTrue(TokenType.isUseful(new Token(t, "", null, -1)));
            }
        }

        // Special case
        assertFalse(TokenType.isUseful(null));
    }
}