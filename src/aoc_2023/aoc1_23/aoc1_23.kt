package aoc_2023.aoc1_23

import getFileAsString
import java.io.File

fun aoc1P1_23(input: String) {
    println("Input Name: $input")
    val file = File(input)

    if (file.exists()) {
        var first: Char? = null
        var second: Char? = null
        var total = 0

        val content = file.readText().trim().iterator()

        while (content.hasNext()) {
            val char = content.next()
            println("Char is: $char")
            when {
                char.isDigit() -> {
                    if (first == null) {
                        println("First Char: $char")
                        first = char
                        second = char
                    } else {
                        println("Second Char: $char")
                        second = char
                    }
                }
                char.isWhitespace() -> {
                    if (first != null) {
                        println("Adding Chars: $first , $second , $total")
                        val number = "$first${second ?: first}".toInt()
                        total += number
                        println("New Total: $total , resetting")
                        first = null
                        second = null
                    }
                }
                else -> {}
            }
        }
        if (first != null) {
            println("Adding Chars: $first , $second , $total")
            val number = "$first${second ?: first}".toInt()
            total += number
            println("New Total: $total , resetting")
        }
        println("Total: $total")
    } else {
        println("File does not exist!")
    }
}

fun aoc1P2_23(input: String) {
    println("Input Name: $input")
    val file = File(input)

    if (file.exists()) {
        val unsanitizedContent = file.readText().split("\\s+".toRegex())
        val sanitizedRows = unsanitizedContent.joinToString("\n") { row ->
            val changes = StringBuilder(row)

            row.indexesOf("one").forEach{ changes[it] = '1' }
            row.indexesOf("two").forEach{ changes[it] = '2' }
            row.indexesOf("three").forEach{ changes[it] = '3' }
            row.indexesOf("four").forEach{ changes[it] = '4' }
            row.indexesOf("five").forEach{ changes[it] = '5' }
            row.indexesOf("six").forEach{ changes[it] = '6' }
            row.indexesOf("seven").forEach{ changes[it] = '7' }
            row.indexesOf("eight").forEach{ changes[it] = '8' }
            row.indexesOf("nine").forEach{ changes[it] = '9' }

            println("Row: $changes")
            changes
        }

        val content = sanitizedRows.iterator()
        var first: Char? = null
        var second: Char? = null
        var total = 0

        while (content.hasNext()) {
            val char = content.next()
            println("Char is: $char")
            when {
                char.isDigit() -> {
                    if (first == null) {
                        println("First Char: $char")
                        first = char
                        second = char
                    } else {
                        println("Second Char: $char")
                        second = char
                    }
                }
                char.isWhitespace() -> {
                    if (first != null) {
                        println("Adding Chars: $first , $second , $total")
                        val number = "$first${second ?: first}".toInt()
                        total += number
                        println("New Total: $total , resetting")
                        first = null
                        second = null
                    }
                }
                else -> {}
            }
        }
        if (first != null) {
            println("Adding Chars: $first , $second , $total")
            val number = "$first${second ?: first}".toInt()
            total += number
            println("New Total: $total , resetting")
        }
        println("Total: $total")
    }
}

fun String.indexesOf(substring: String): List<Int> {
    val indices = mutableListOf<Int>()
    var index = this.indexOf(substring)

    while (index >= 0) {
        indices.add(index)
        index = this.indexOf(substring, index + 1) // Start search from the next position
    }

    return indices
}