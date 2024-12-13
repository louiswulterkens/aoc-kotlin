package aoc_2024

import aoc_2024.Point2D.Companion.EAST
import aoc_2024.Point2D.Companion.NORTH
import aoc_2024.Point2D.Companion.SOUTH
import aoc_2024.Point2D.Companion.WEST
import java.nio.channels.Selector

class Day12(input: List<String>) {

    val farm: List<CharArray> = input.map { it.toCharArray() }

    var visited = HashMap<Point2D, Boolean>()

    fun solvePart1(): Int {
        val perimeters = farm.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                Point2D(x, y).let {
                    if (visited[it] == null) {
                        fenceItOff(Point2D(x, y), c)
                    } else null
                }
            }.filterNotNull()
        }.sumOf {
            it.first * it.second
        }

        return perimeters
    }

    private fun fenceItOff(point: Point2D, c: Char): Triple<Int, Int, Int> {
        if (farm[point] != c) { return Triple(0, 1, 0)}
        if (visited[point] == true) return Triple(0, 0, 0)
        visited[point] = true

        val areaPerimeterCorner: Triple<Int, Int, Int> = Point2D.directions
            .map { dir -> fenceItOff(point + dir, c) }
            .let { Triple(
                it.sumOf { areas -> areas.first },
                it.sumOf { perimeters -> perimeters.second },
                it.sumOf { corners -> corners.third})
            }

        val corners = point.countCorners()

        return areaPerimeterCorner.let { (first, second, third) -> Triple(first + 1, second, third + corners) }
    }

    fun solvePart2(): Int {
        val withCorners = farm.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                Point2D(x, y).let {
                    if (visited[it] == null) {
                        fenceItOff(Point2D(x, y), c)
                    } else null
                }
            }.filterNotNull()
        }.sumOf {
            it.first * it.third
        }

        return withCorners
    }


    private fun Point2D.countCorners(): Int =
        listOf(NORTH, EAST, SOUTH, WEST, NORTH)
            .zipWithNext()
            .map { (first, second) ->
                listOf(
                    farm[this],
                    farm[this + first],
                    farm[this + second],
                    farm[this + first + second]
                )
            }.count { (current, side1, side2, corner) ->
                (current != side1 && current != side2) ||
                (side1 == current && side2 == current && corner != current)
            }

    operator fun Pair<Int, Int>.plus(value: Pair<Int, Int>) = this.first + value.first to this.second + value.second
}



