package aoc_2024.aoc2_24

import kotlin.math.abs

val oneOrMoreDigits = Regex("""(\d+)""")

fun aoc2P1_24(input: List<String>) {

    var total = 0

    input.forEach { line ->
        val digits = oneOrMoreDigits.findAll(line).map { it.value.toInt() }.toList()
        if (safeLine(digits) < 0) {
            total++
        }
    }
    println("Total: $total")
}

fun aoc2P2_24(input: List<String>) {
    var total = 0

    input.forEach { line ->
        val digits = oneOrMoreDigits.findAll(line).map { it.value.toInt() }.toList()
        if (safeLine(digits) < 0) {
            total++
            return@forEach
        }
        for (i in digits.indices) {
            val newDigits = digits.filterIndexed { index, _ -> index != i  }
            if (safeLine(newDigits) < 0) {
                total++
                return@forEach
            }
        }
        println("Failure: $line")
    }

    println("Total: $total")
}

private fun safeIncrement(last: Int, current: Int, positive: Boolean?): Boolean {
    val diff = last - current
    if (abs(diff) in 1..3) {
        positive?.let {
            if (positive != diff > 0) {
                return false
            }
        }
    } else {
        return false
    }
    return true
}

// returns problematic index
private fun safeLine(digits: List<Int>): Int {
    var positive: Boolean? = null

    for (i in 1..<digits.size) {
        if (!safeIncrement(digits[i-1], digits[i], positive)) {
            return i
        }
        if (i == 1) positive = digits[0] - digits[1] > 0
    }
    return -1
}