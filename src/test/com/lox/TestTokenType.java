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
                assertFalse(TokenType.isUseful(t));
            }
            else {
                assertTrue(TokenType.isUseful(t));
            }
        }

        // Special case
        assertFalse(TokenType.isUseful(null));
    }
}
