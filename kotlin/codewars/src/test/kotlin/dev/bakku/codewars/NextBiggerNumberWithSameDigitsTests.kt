package dev.bakku.codewars

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NextBiggerNumberWithSameDigitsTests {
    @Test
    fun `nextBiggerNumber should return the correct next bigger numbers`() {
        assertEquals(21, nextBiggerNumber(12))
        assertEquals(531, nextBiggerNumber(513))
        assertEquals(2071, nextBiggerNumber(2017))
        assertEquals(441, nextBiggerNumber(414))
        assertEquals(414, nextBiggerNumber(144))
        assertEquals(1234567908, nextBiggerNumber(1234567890))
        assertEquals(-1, nextBiggerNumber(9876543210))
    }
}