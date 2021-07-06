package dev.bakku.codewars

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DeodorantEvaporatorTests {
    @Test
    fun evaporatorShouldReturnCorrectResults() {
        assertEquals(22, evaporator(10.0,10.0,10.0))
        assertEquals(29, evaporator(10.0,10.0,5.0))
        assertEquals(59, evaporator(100.0,5.0,5.0))
    }
}