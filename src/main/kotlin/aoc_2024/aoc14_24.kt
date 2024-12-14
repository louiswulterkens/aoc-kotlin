package aoc_2024

class Day14(input: List<String>) {

    private var width = 101
    private var height = 103

    private val guards = input.map { getGuard(it) }

    fun solvePart1(example: Boolean = false): Int {
        if (example) {
            width = 11
            height = 7
        }
        val finalDestinations = guards
            .mapNotNull {
                val fp = it.getFinalPosition(100) // finalPosition
                when {
                    fp.x < width/2 && fp.y < height/2 -> 0 to fp
                    fp.x > (width/2) && fp.y < height/2 -> 1 to fp
                    fp.x < width/2 && fp.y > (height/2) -> 2 to fp
                    fp.x > (width/2) && fp.y > (height/2) -> 3 to fp
                    else -> null
                }
            }
        return finalDestinations
            .groupBy { it.first }
            .map { it.value.size }
            .reduce { acc, i -> acc * i  }
    }

    fun solvePart2(): Int {
        var seconds = 1
        val potentials: MutableSet<Int> = mutableSetOf()

        while(potentials.size < 4) {
            guards
                .map { it.getFinalPosition(seconds) }
                .groupBy { it.y }
                .forEach {
                    val sorted = it.value.sortedBy { it.x }

                    if (sorted.size < 10) return@forEach

                    var longest = 1
                    var current = 1
                    (0..<sorted.size-1).forEach { i ->
                        if (sorted[i].x + 1 == sorted[i+1].x) {
                            current++
                        } else {
                            current = 1
                        }
                        if (current > longest) longest = current
                    }
                    if (longest > 10) {
                        potentials.add(seconds)
                    }
                }
            seconds++
        }

        potentials.forEach { printGrid(it) }

        return potentials.first()
    }



    private fun printGrid(seconds: Int) {
        val snapshot = guards.map { it.getFinalPosition(seconds) }
        println("Seconds: $seconds")
        (0..100).forEach { x ->
            println("")
            (0..103).forEach { y ->
                if (Point2D(x, y) in snapshot ) {
                    print("\u25A1")
                } else {
                    print(".")
                }
            }
        }
    }

    private fun Guard.getFinalPosition(seconds: Int): Point2D {
        val distance = vector * seconds
        val ending = position + distance
        return Point2D(ending.x.mod(width), ending.y.mod(height))
    }

    private operator fun Point2D.times(num: Int) = Point2D(x * num, y * num)

    private fun getGuard(input: String): Guard =
        Regex("""p=(-?\d+),(-?\d+) v=(-?\d+),(-?\d+)""").find(input)?.groupValues?.drop(1)?.map { it.toInt() }?.let {
            Guard(
                position = Point2D(it[0], it[1]),
                vector = Point2D(it[2], it[3])
            )
        } ?: throw Exception("Failed to parse guard")

    private class Guard(
        val position: Point2D,
        val vector: Point2D
    )
}



