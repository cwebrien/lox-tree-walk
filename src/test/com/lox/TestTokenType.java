package com.lox;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestTokenType {
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
