package dev.bakku.codewars

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PartsOfAListTests {
    @Test
    fun testPartsOfAList() {
        var s1 = arrayOf("cdIw", "tzIy", "xDu", "rThG")
        var a = "[[cdIw, tzIy xDu rThG], [cdIw tzIy, xDu rThG], [cdIw tzIy xDu, rThG]]"
        assertEquals(a, partsOfAList(s1).contentDeepToString())
        s1 = arrayOf("I", "wish", "I", "hadn't", "come")
        a = "[[I, wish I hadn't come], [I wish, I hadn't come], [I wish I, hadn't come], [I wish I hadn't, come]]"
        assertEquals(a, partsOfAList(s1).contentDeepToString())
    }
}