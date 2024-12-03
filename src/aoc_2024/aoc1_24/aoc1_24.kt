package aoc_2024.aoc1_24

import kotlin.math.abs

fun aoc1P1_24(input: List<String>) {
    val list1: MutableList<Int> = mutableListOf()
    val list2: MutableList<Int> = mutableListOf()

    input.forEach { pair ->
        Regex("""(\d+) {3}(\d+)""").find(pair.trim())?.groupValues.let {
            list1.add(it?.get(1)?.toInt() ?: throw Exception("Invalid input: index 1"))
            list2.add(it?.get(2)?.toInt() ?: throw Exception("Invalid input: index 2"))
        }
    }

    list1.sort()
    list2.sort()

    var total = 0
    for (i in 0..<list1.size) {
        total += abs(list1[i] - list2[i])
    }

    println("Total: $total")
}

fun aoc1P2_24(input: List<String>) {
    val list1: MutableList<Int> = mutableListOf()
    val list2: MutableList<Int> = mutableListOf()

    input.forEach { pair ->
        Regex("""(\d+) {3}(\d+)""").find(pair.trim())?.groupValues.let {
            list1.add(it?.get(1)?.toInt() ?: throw Exception("Invalid input: index 1"))
            list2.add(it?.get(2)?.toInt() ?: throw Exception("Invalid input: index 2"))
        }
    }

    val map: MutableMap<Int, Int> = mutableMapOf()
    var totalSimilarity = 0
    list1.forEach { value ->
        val valueSimilarity = map.get(value) ?: (list2.filter { it == value }.size * value)
        map[value] = valueSimilarity
        totalSimilarity += valueSimilarity
    }

    println("Similarity: $totalSimilarity")
}