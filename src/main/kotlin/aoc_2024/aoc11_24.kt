package aoc_2024

class Day11(input: String, val reptitions: Int) {

    private val stones = input.split(" ").map { it.toLong() }

    // For Posterity, I kept my original answer to P1 even though I improved it.
    // My first pass was splitting/combining lists, which obviously runs out of space on higher repetitions

    fun solvePart1(): Long {
        return stones.sumOf { digitStep(it, reptitions) }
//        var result = stones.toList()
//        (1..reptitions).map {
//            result = result.map { num ->
//                when {
//                    num == 0L -> listOf(1L)
//                    num.digits()%2 == 0 -> num.splitInTwo()
//                    else -> listOf(num * 2024)
//                }
//            }.flatten()
//        }
//
//        return result.size
    }

    fun solvePart2(): Long {
        return stones.sumOf { digitStep(it, reptitions) }
    }

    private val hashMap = HashMap<Pair<Long, Int>, Long>()
    private fun digitStep(num: Long, step: Int): Long {
        if (step == 0) return 1L
        hashMap[num to step]?.let { return it }
        hashMap[num to step] = when {
            num == 0L -> digitStep(1L, step - 1)
            num.digits()%2 == 0 -> num.splitInTwo().sumOf { digitStep(it, step - 1) }
            else -> digitStep(num * 2024, step -1)
        }
        return hashMap[Pair(num, step)]!!
    }

    private fun Long.digits(): Int = this.toString().length

    private fun Long.splitInTwo(): List<Long> = this.toString().let { listOf(it.substring(0, (it.length/2)).toLong(), it.substring(it.length/2).toLong()) }
}



