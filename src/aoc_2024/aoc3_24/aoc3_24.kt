package aoc_2024.aoc3_24

val multRegex = Regex("""mul\((\d+),(\d+)\)""")
val multDo_DoNotRegex = Regex("""mul\((\d+),(\d+)\)|do\(\)|don't\(\)""")

fun aoc3P1_24(input: String) {
    var total = 0
    multRegex.findAll(input).forEach { multPhrase ->
        total += multPhrase.groupValues.let { it[1].toInt() * it[2].toInt() }
    }
    println("Total: $total")
}


fun aoc3P2_24(input: String) {
    var total = 0
    var doCalc = true
    multDo_DoNotRegex.findAll(input).forEach { x ->
        when (val phrase = x.value) {
            "do()" -> doCalc = true
            "don't()" -> doCalc = false
            else -> if (doCalc) {
                total += multRegex.find(phrase)?.groupValues?.let { it[1].toInt() * it[2].toInt() } ?: 0
            }
        }
    }
    println("Total: $total")
}