package main.kotlin.aoc_2024.aoc5_24

val rulesRegex = Regex("""(\d+)\|(\d+)""")
val commaDelimitedDigits = Regex("""(\d+)""")

fun findValidIndexes(rulesList: List<String>, pagesList: List<List<Int>>): List<Int> {
    val rules = mutableMapOf<Int, Set<Int>>()
    var indexes: List<Int> = listOf()

    rulesList.forEach { line ->
        rulesRegex.find(line)?.groupValues?.let {
            rules[it[1].toInt()] = ( rules[it[1].toInt()] ?: setOf() ) + it[2].toInt()
        }
    }

    pagesList.forEachIndexed { index, instruction ->
        if (checkValidInstruction(instruction, rules)) {
            indexes = indexes.plus(index)
        }
    }

    return indexes
}

fun checkValidInstruction(instruction: List<Int>, rules: Map<Int, Set<Int>>): Boolean {
    val validDigits = instruction.mapIndexed { xIndex, digit ->
        // get the rules for the current digits
        val digitRules = rules[digit] ?: setOf()

        // Get only the numbers that pertain to that digits rules
        val ys = instruction.filter { digitRules.contains(it) }

        // For each Y
        val validYIndexes = ys.map { y ->
            instruction
                // get all indexes of that Y
                .mapIndexedNotNull { index, i -> if (i == y) index else null }
                // Check that at least 1 of the Y-instances appears after the X
                // Wording said "X must be printed at some point before Y", so if rule 3|4 was used on 3,4,3, that should be valid because there's a 3 before the 4
                .any { yIndex -> yIndex > xIndex }
        }
        // Check that none of the digits failed
        !validYIndexes.contains( false )
    }
    return !validDigits.contains(false)
}

fun aoc5P1_24(input: List<String>) {
    val splitIndex = input.indexOfFirst { it.isBlank() }
    val rulesList = input.subList(0, splitIndex)
    val pagesList = input.subList(splitIndex + 1, input.size).map { commaDelimitedDigits.findAll(it).map { it.value.toInt() }.toList() }

    val validIndexes = findValidIndexes(rulesList, pagesList)

    val total: Int = validIndexes.sumOf {
        pagesList[it][pagesList[it].size/2]
    }

    println("Total: $total")
}

fun aoc5P2_24(input: List<String>) {
    val splitIndex = input.indexOfFirst { it.isBlank() }
    val rulesList = input.subList(0, splitIndex)
    val pagesList = input.subList(splitIndex + 1, input.size).map { commaDelimitedDigits.findAll(it).map { it.value.toInt() }.toList() }

    // list of invalid pages
    val validIndexes = findValidIndexes(rulesList, pagesList)
    val invalidPagesList = pagesList.filterIndexed{ index, _ -> !validIndexes.contains(index) }

    // rules
    val rules = mutableMapOf<Int, Set<Int>>()
    rulesList.forEach { line ->
        rulesRegex.find(line)?.groupValues?.let {
            rules[it[1].toInt()] = ( rules[it[1].toInt()] ?: setOf() ) + it[2].toInt()
        }
    }

    val total = invalidPagesList.sumOf { instruction ->
        val ordered = addToSolution(instruction = instruction, index = 0, partialSolution = listOf(), rules = rules)
        ordered?.let { it[it.size/2] } ?: throw Exception("Didn't Get Solution")
    }

    println("Total: $total")
}


// Recursive function, keep swapping and passing the list until it's valid, then return the middle.
fun findMiddle(instruction: List<Int>, rules: Map<Int, Set<Int>>): Int {
    // Get the failing X + Y index and swap
    val failed = instruction.mapIndexed { xIndex, digit ->
        val digitRules = rules[digit] ?: setOf()

        // Get only the numbers that pertain to that digits rules
        val ys = instruction.filter { digitRules.contains(it) }

        // For each Y
        val failedYIndex = ys.map { y ->
            // get all indexes of that Y
            instruction
                // get all indexes of that Y
                .mapIndexedNotNull { index, i -> if (i == y) index else null }
                // find all instances of Y appearing before X
                .find { yIndex -> yIndex > xIndex }
        }
            // Grab the first failed Y instance (or Null if nothing failed)
            .firstOrNull { it != null }
        // If we found a failing Y, send back the X-Y problematic pair
        if (failedYIndex != null) Pair(xIndex, failedYIndex) else null
    }.firstOrNull { it != null }

    // If no failing pair was found, the instruction is now valid and we can grab the middle
    return if (failed == null) {
         instruction[instruction.size/2]
    } else {
        // Move the invalid Y to the index before the X, then recur
        val newInstruction = instruction.toMutableList().apply {
            removeAt(failed.second)
            add(failed.first, instruction[failed.second])
        }.toList()
        findMiddle(newInstruction, rules)
    }
}

fun addToSolution(instruction: List<Int>, index: Int, partialSolution: List<Int>, rules: Map<Int, Set<Int>>): List<Int>? {
    if (partialSolution.size == instruction.size) return partialSolution

    for (i in 0..partialSolution.size) {
        val testing = partialSolution.toMutableList().apply { add(i, instruction[index]) }
        if (checkValidInstruction(testing, rules)) {
            return addToSolution(instruction, index+1, testing, rules)
        }
    }
    return null
}

