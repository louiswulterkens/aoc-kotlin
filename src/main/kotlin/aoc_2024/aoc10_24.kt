package aoc_2024

class Day10(input: List<String>) {

    private val topography: List<List<Int>> = input.map { line -> line.toCharArray().map { it.code - '0'.code } }

    fun solvePart1(): Int =
        topography
            .getHeads()
            .sumOf { findNextTopo(it, 1).size }

    fun solvePart2(): Int =
        topography
            .getHeads()
            .sumOf { findNextTopoRating(it, 1) }


    private fun findNextTopo(point: Point2D, num: Int): Set<Point2D> =
        if (topography[point] == 9) {
            setOf(point)
        } else {
            Point2D.directions.mapNotNull { dir ->
                (point + dir).let { newPoint ->
                    if (topography[newPoint] == num) {
                        findNextTopo(newPoint, num + 1)
                    } else null
                }
            }.flatten().toSet()
        }

    private fun findNextTopoRating(point: Point2D, num: Int): Int =
        if (topography[point] == 9) {
            1
        } else {
            Point2D.directions.sumOf { dir ->
                (point + dir).let { newPoint ->
                    if (topography[newPoint] == num) {
                        findNextTopoRating(newPoint, num + 1)
                    } else 0
                }
            }
        }

    private fun List<List<Int>>.getHeads(): List<Point2D> =
        this.mapIndexed { y, heights ->
            heights.mapIndexedNotNull { x, height ->
                if (height == 0) Point2D(x, y) else null
            }
        }.flatten()
}