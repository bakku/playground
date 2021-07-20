package dev.bakku.codewars

fun nextBiggerNumber(n: Long): Long {
    fun <T> List<T>.tally(): Map<T, Int> = this.groupingBy { it }.eachCount()
    fun maxNum(n: Long): Long = n.toString().toList().sortedDescending().joinToString("").toLong()
    fun isPermutation(orig: Long, permutation: Long): Boolean =
        orig.toString().toList().tally() == permutation.toString().toList().tally()

    val permutation = ((n+1)..maxNum(n)).firstOrNull { isPermutation(n, it) }

    return permutation ?: -1
}