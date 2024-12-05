package aoc_2024.aoc5_24

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
        val digitRules = rules[digit] ?: setOf()

        val ys = instruction.filter { digitRules.contains(it) }
        val validYIndexes = ys.map { y ->
            instruction.mapIndexedNotNull { index, i -> // find all indexes of Y
                if (i == y) index else null
            }.any { yIndex ->
                yIndex > xIndex
            }
        }

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

    val total = invalidPagesList.sumOf {
        findMiddle(it, rules)
    }

    println("Total: $total")
}

fun findMiddle(instruction: List<Int>, rules: Map<Int, Set<Int>>): Int {
    val failed = instruction.mapIndexed { xIndex, digit ->
        val digitRules = rules[digit] ?: setOf()

        val ys = instruction.filter { digitRules.contains(it) }
        val failedYIndex = ys.map { y ->
            instruction.mapIndexedNotNull { index, i -> // find all indexes of Y
                if (i == y) index else null
            }.find { yIndex ->
                yIndex > xIndex
            }
        }.firstOrNull { it != null }
        if (failedYIndex != null) Pair(xIndex, failedYIndex) else null
    }.firstOrNull { it != null }

    return if (failed == null) {
         instruction[instruction.size/2]
    } else {
        val newInstruction = instruction.toMutableList().apply {
            removeAt(failed.second)
        }.apply {
            add(failed.first, instruction[failed.second])
        }.toList()
        findMiddle(newInstruction, rules)
    }
}