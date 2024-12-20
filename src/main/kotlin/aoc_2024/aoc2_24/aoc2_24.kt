package main.kotlin.aoc_2024.aoc2_24

val oneOrMoreDigits = Regex("""(\d+)""")

fun aoc2P1_24(input: List<String>) {

    var total = 0

    input.forEach { line ->
        val digits = oneOrMoreDigits.findAll(line).map { it.value.toInt() }.toList()
        if (safeLine(digits)) {
            total++
        }
    }
    println("Total: $total")
}

fun aoc2P2_24(input: List<String>) {
    val total = input.count {
        val digits = oneOrMoreDigits.findAll(it).map { it.value.toInt() }.toList()
        digits.indices.any { toRemove ->
            safeLine(digits.filterIndexed { index, _ -> index != toRemove  })
        }
    }

//    input.forEach { line ->
//        val digits = oneOrMoreDigits.findAll(line).map { it.value.toInt() }.toList()
//        for (i in digits.indices) {
//            val newDigits = digits.filterIndexed { index, _ -> index != i  }
//            if (safeLine(newDigits)) {
//                total++
//                return@forEach
//            }
//        }
//    }

    println("Total: $total")
}

// returns problematic index
private fun safeLine(digits: List<Int>): Boolean {
    val diffs = digits.zipWithNext { a, b -> b - a }
    val increasing = diffs.all { it in 1..3 }
    val decreasing = diffs.all { it in -3..-1 }
    return increasing || decreasing
}