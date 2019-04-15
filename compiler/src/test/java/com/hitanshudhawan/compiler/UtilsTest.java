package com.hitanshudhawan.compiler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void capitalize() {
        assertEquals(Utils.capitalize("id"), "Id");
        assertEquals(Utils.capitalize("location"), "Location");
        assertEquals(Utils.capitalize("ABTesting"), "ABTesting");
        assertEquals(Utils.capitalize("user"), "User");
    }
}