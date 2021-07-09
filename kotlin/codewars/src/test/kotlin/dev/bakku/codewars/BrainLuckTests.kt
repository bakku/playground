package dev.bakku.codewars

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BrainLuckTests {
    @Test
    fun testEchoUntilByte255Encountered() {
        assertEquals("Codewars", BrainLuck(",+[-.,+]").process("Codewars" + 255.toChar()))
    }

    @Test
    fun testEchoUntilByte0Encountered() {
        assertEquals("Codewars", BrainLuck(",[.[-],]").process("Codewars" + 0.toChar()))
    }

    @Test
    fun testTwoNumbersMultiplier() {
        val input = charArrayOf(8.toChar(), 9.toChar())
        assertEquals(
            (input[0].code * input[1].code).toChar().toString(),
            BrainLuck(",>,<[>[->+>+<<]>>[-<<+>>]<<<-]>>.").process(input[0].toString() + input[1].toString())
        )
    }
}