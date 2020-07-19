package com.lox;

import org.junit.Assert;
import org.junit.Test;

public class TestToken {
    /**
     * Tests string conversion of Token class.
     */
    @Test
    public void testToString() {
        Token t = new Token(TokenType.IDENTIFIER, "some_variable_name", null, 5);
        Assert.assertEquals("IDENTIFIER some_variable_name null 5", t.toString());
    }
}
