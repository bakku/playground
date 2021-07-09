package dev.bakku.codewars

/*
 * We want to create an interpreter of a language which will support the following instructions:
 *
 * > increment the data pointer (to point to the next cell to the right).
 * < decrement the data pointer (to point to the next cell to the left).
 * + increment (increase by one, truncate overflow: 255 + 1 = 0) the byte at the data pointer.
 * - decrement (decrease by one, treat as unsigned byte: 0 - 1 = 255 ) the byte at the data pointer.
 * . output the byte at the data pointer.
 * , accept one byte of input, storing its value in the byte at the data pointer.
 * [ if the byte at the data pointer is zero, then instead of moving the instruction pointer
 *   forward to the next command, jump it forward to the command after the matching ] command.
 * ] if the byte at the data pointer is nonzero, then instead of moving the instruction pointer
 *   forward to the next command, jump it back to the command after the matching [ command.
 *
 * The function will take in input...
 * 
 * - the program code, a string with the sequence of machine instructions,
 * - the program input, a string, eventually empty, that will be interpreted as an array of bytes
 *   using each character's ASCII code and will be consumed by the , instruction
 *
 * ... and will return ...
 *
 * the output of the interpreted code (always as a string), produced by the . instruction.
 */
class BrainLuck(private val code: String) {
    private var data = Array(30000) { 0.toChar() }
    private var output = ArrayList<Char>()

    private var dataPointer: Int = 0
    private var instructionPointer: Int = 0
    private var inputReadPointer: Int = 0
    private var outputWritePointer: Int = 0

    fun process(input: String): String {
        while (instructionPointer < code.length) executeNextCommand(input)

        return output.joinToString("")
    }

    private fun executeNextCommand(input: String) {
        when (code[instructionPointer]) {
            '>' -> incrementDataPointer()
            '<' -> decrementDataPointer()
            '+' -> increment()
            '-' -> decrement()
            '.' -> saveToOutput()
            ',' -> readFromInput(input)
            '[' -> jumpIfZero()
            ']' -> jumpIfNonZero()
        }
    }

    private fun incrementDataPointer() {
        dataPointer++
        instructionPointer++
    }

    private fun decrementDataPointer() {
        dataPointer--
        instructionPointer++
    }

    private fun increment() {
        data[dataPointer] = ((data[dataPointer].code + 1) % 256).toChar()
        instructionPointer++
    }

    private fun decrement() {
        data[dataPointer] = data[dataPointer].let {
            val newVal = if ((it.code - 1) == -1) 255 else it.code - 1
            newVal.toChar()
        }
        instructionPointer++
    }

    private fun saveToOutput() {
        output.add(data[dataPointer])
        instructionPointer++
        outputWritePointer++
    }

    private fun readFromInput(input: String) {
        data[dataPointer] = input[inputReadPointer]
        inputReadPointer++
        instructionPointer++
    }

    private fun jumpIfZero() {
        val value = data[dataPointer]

        // Jump to command AFTER matching end brace
        if (value.code == 0) jumpToMatchingEndBrace()

        instructionPointer++
    }

    private fun jumpIfNonZero() {
        val value = data[dataPointer]

        // Jump to command AFTER matching start brace
        if (value.code != 0) jumpToMatchingStartBrace()

        instructionPointer++
    }

    private fun jumpToMatchingEndBrace() {
        // How many start braces were found --> determines the matching end brace
        var amountStartBraces = 0
        instructionPointer++

        while (amountStartBraces != 0 || code[instructionPointer] != ']') {
            if (code[instructionPointer] == ']') amountStartBraces--
            if (code[instructionPointer] == '[') amountStartBraces++

            instructionPointer++
        }
    }

    private fun jumpToMatchingStartBrace() {
        // How many end braces were found --> determines the matching start brace
        var amountEndBraces = 0
        instructionPointer--

        while (amountEndBraces != 0 || code[instructionPointer] != '[') {
            if (code[instructionPointer] == ']') amountEndBraces++
            if (code[instructionPointer] == '[') amountEndBraces--

            instructionPointer--
        }
    }
}