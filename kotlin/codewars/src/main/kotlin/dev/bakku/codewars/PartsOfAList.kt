package dev.bakku.codewars

/*
 * Write a function partlist that gives all the ways to divide a list (an array)
 * of at least two elements into two non-empty parts.
 *
 * Each two non empty parts will be in a array.
 *
 * Each part will be in a string
 *
 * Elements of a pair must be in the same order as in the original array.
 *
 * Example:
 *
 * a = ["az", "toto", "picaro", "zone", "kiwi"] -->
 * [["az", "toto picaro zone kiwi"], ["az toto", "picaro zone kiwi"],
 *  ["az toto picaro", "zone kiwi"], ["az toto picaro zone", "kiwi"]]
 */
fun partsOfAList(arr: Array<String>): Array<Array<String>> {
    return Array(arr.size - 1) { arr }
        .mapIndexed { idx, a ->
            arrayOf(a.take(idx + 1).joinToString(" ")) +
                    arrayOf(a.drop(idx + 1).joinToString(" "))
        }
        .toTypedArray()
}