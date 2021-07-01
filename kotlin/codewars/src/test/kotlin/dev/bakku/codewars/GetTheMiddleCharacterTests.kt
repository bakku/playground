package dev.bakku.codewars

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GetTheMiddleCharacterTests {
    @Test
    fun testGetTheMiddleCharacter() {
        assertEquals("es", getTheMiddleCharacter("test"));
        assertEquals("dd", getTheMiddleCharacter("middle"));
        assertEquals("t", getTheMiddleCharacter("testing"));
        assertEquals("A", getTheMiddleCharacter("A"));
    }
}