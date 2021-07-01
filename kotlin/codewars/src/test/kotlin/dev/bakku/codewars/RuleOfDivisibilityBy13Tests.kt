package dev.bakku.codewars

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RuleOfDivisibilityBy13Tests() {
    @Test
    fun testRuleOfDivisibilityBy13() {
        assertEquals(87, ruleOfDivisibilityBy13(1234567))
        assertEquals(48, ruleOfDivisibilityBy13(321))
    }
}
