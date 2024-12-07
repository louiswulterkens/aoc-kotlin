package aoc_2024

class Day07(input: List<String>) {

    private val equations = parse(input)

    fun solvePart1(): Long =
        equations.filter { (total, nums) ->
            generateAnswers(nums[0], nums.drop(1)).any {
                it == total
            }
        }.sumOf { (total, _) -> total}

    private fun generateAnswers(total: Long, nums: List<Long> ): List<Long> {
        if (nums.isEmpty()) return listOf(total)

        val additionAnswers = generateAnswers(total + nums[0], nums.drop(1))
        val multiplyAnswers = generateAnswers(total * nums[0], nums.drop(1))
        return additionAnswers + multiplyAnswers
    }

    fun solvePart2(): Long =
        equations.filter { (total, nums) ->
            val worked = generateAnswersMore(nums[0], nums.drop(1)).any {
                it == total
            }
            worked
        }.sumOf { (total, _) -> total}

    private fun generateAnswersMore(total: Long, nums: List<Long> ): List<Long> {
        if (nums.isEmpty()) return listOf(total)

        val additionAnswers = generateAnswersMore(total + nums[0], nums.drop(1))
        val multiplyAnswers = generateAnswersMore(total * nums[0], nums.drop(1))
        val concatAnswers = generateAnswersMore("$total${nums[0]}".toLong(), nums.drop(1))
        return additionAnswers + multiplyAnswers + concatAnswers
    }

    private fun parse(input: List<String>): List<Pair<Long, List<Long>>> =
        input.map { row ->
            row.split(": ").let {
                Pair(it[0].toLong(), it[1].split(" ").map { it.toLong() })
            }
        }

    private fun List<String>.safeAt(x: Int, y: Int): Char =
        if(y in this.indices && x in this[y].indices) this[y][x] else ' '

    private fun List<CharArray>.set(x: Int, y: Int, value: Char) = {

    }

    private fun List<String>.isBlocked(x: Int, y: Int): Boolean =
        this.safeAt(x, y) == '#'
}



