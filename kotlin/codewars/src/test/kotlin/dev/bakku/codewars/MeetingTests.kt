package dev.bakku.codewars

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MeetingTests {
    @Test
    fun meetingShouldReturnCorrectResults() {
        assertEquals(
            """
                (ARNO, ANN)(BELL, JOHN)(CORNWELL, ALEX)
                (DORNY, ABBA)(KERN, LEWIS)(KORN, ALEX)
                (META, GRACE)(SCHWARZ, VICTORIA)(STAN, MADISON)
                (STAN, MEGAN)(WAHL, ALEXIS)
            """.trimIndent().replace("\n", ""),
            meeting(
                """
                    Alexis:Wahl;John:Bell;Victoria:Schwarz;
                    Abba:Dorny;Grace:Meta;Ann:Arno;
                    Madison:STAN;Alex:Cornwell;Lewis:Kern;
                    Megan:Stan;Alex:Korn
                """.trimIndent().replace("\n", "")
            )
        )

        assertEquals(
            """
                (BELL, MEGAN)(CORNWELL, AMBER)(DORNY, JAMES)
                (DORRIES, PAUL)(GATES, JOHN)(KERN, ANN)
                (KORN, ANNA)(META, ALEX)(RUSSEL, ELIZABETH)
                (STEVE, LEWIS)(WAHL, MICHAEL)
            """.trimIndent().replace("\n", ""),
            meeting(
                """
                    John:Gates;Michael:Wahl;Megan:Bell;
                    Paul:Dorries;James:Dorny;Lewis:Steve;
                    Alex:Meta;Elizabeth:Russel;Anna:Korn;
                    Ann:Kern;Amber:Cornwell
                """.trimIndent().replace("\n", "")
            )
        )

        assertEquals(
            """
                (ARNO, ALEX)(ARNO, HALEY)(BELL, SARAH)
                (CORNWELL, ALISSA)(DORNY, PAUL)
                (DORRIES, ANDREW)(KERN, ANN)(KERN, MADISON)
            """.trimIndent().replace("\n", ""),
            meeting(
                """
                    Alex:Arno;Alissa:Cornwell;Sarah:Bell;
                    Andrew:Dorries;Ann:Kern;Haley:Arno;
                    Paul:Dorny;Madison:Kern
                """.trimIndent().replace("\n", "")
            )
        )
    }
}