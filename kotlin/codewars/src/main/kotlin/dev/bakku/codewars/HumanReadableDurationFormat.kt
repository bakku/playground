package dev.bakku.codewars

/*
 * Your task in order to complete this Kata is to write a function which formats a duration,
 * given as a number of seconds, in a human-friendly way.
 *
 * The function must accept a non-negative integer. If it is zero, it just returns "now".
 * Otherwise, the duration is expressed as a combination of years, days, hours, minutes and seconds.
 *
 * It is much easier to understand with an example:
 *
 * formatDuration (62)    // returns "1 minute and 2 seconds"
 * formatDuration (3662)  // returns "1 hour, 1 minute and 2 seconds"
 *
 * For the purpose of this Kata, a year is 365 days and a day is 24 hours.
 *
 * Note that spaces are important.
 *
 * Detailed rules
 *
 * The resulting expression is made of components like 4 seconds, 1 year, etc.
 * In general, a positive integer and one of the valid units of time, separated by a space.
 * The unit of time is used in plural if the integer is greater than 1.
 *
 * The components are separated by a comma and a space (", "). Except the last component,
 * which is separated by " and ", just like it would be written in English.
 *
 * A more significant units of time will occur before than a least significant one.
 * Therefore, 1 second and 1 year is not correct, but 1 year and 1 second is.
 *
 * Different components have different unit of times. So there is not repeated units like in 5 seconds and 1 second.
 *
 * A component will not appear at all if its value happens to be zero.
 * Hence, 1 minute and 0 seconds is not valid, but it should be just 1 minute.
 *
 * A unit of time must be used "as much as possible". It means that the function should not return 61 seconds,
 * but 1 minute and 1 second instead. Formally, the duration specified by of a component
 * must not be greater than any valid more significant unit of time.
 */

object TimeFormatter {
    private data class Duration(val threshold: Int, val humanReadableForm: String)
    private data class OccurrencesResult(val occurrences: Int, val remainder: Int)

    private const val oneMinute = 60
    private const val oneHour = oneMinute * 60
    private const val oneDay = oneHour * 24
    private const val oneYear = oneDay * 365

    private val orderedDurations = listOf(
        Duration(oneYear, "year"),
        Duration(oneDay, "day"),
        Duration(oneHour, "hour"),
        Duration(oneMinute, "minute"),
        Duration(1, "second")
    )

    fun formatDuration(seconds: Int): String {
        data class FoldIteration(val remainingSeconds: Int, val results: List<String>)

        if (seconds == 0) return "now"

        val (_, results) = orderedDurations.fold(FoldIteration(seconds, ArrayList())) { iteration, currentDuration ->
            val (occurrences, remainder) = countOccurrences(iteration.remainingSeconds, currentDuration.threshold)

            if (occurrences != 0)
                FoldIteration(remainder, iteration.results + "$occurrences ${pluralize(occurrences, currentDuration.humanReadableForm)}")
            else
                iteration
        }

        return if (results.size <= 2) results.joinToString(" and ")
        else listOf(
            results.subList(0, results.lastIndex).joinToString(", "),
            results.last()
        ).joinToString(" and ")
    }

    private fun pluralize(count: Int, singularText: String): String {
        return if (count == 1) singularText
        else singularText + "s"
    }

    private fun countOccurrences(biggerNum: Int, smallerNum: Int): OccurrencesResult {
        if (biggerNum < smallerNum) return OccurrencesResult(0, 0)
        return OccurrencesResult(biggerNum / smallerNum, biggerNum % smallerNum)
    }
}
