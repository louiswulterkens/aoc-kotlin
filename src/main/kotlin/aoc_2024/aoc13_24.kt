package aoc_2024

import aoc_2024.Point2D.Companion.EAST
import aoc_2024.Point2D.Companion.NORTH
import aoc_2024.Point2D.Companion.SOUTH
import aoc_2024.Point2D.Companion.WEST
import java.awt.Button
import java.nio.channels.Selector

class Day13(input: List<String>) {

    val buttonsAndPrizes = parse(input)

    fun solvePart1(): Int =
        buttonsAndPrizes
            .mapNotNull { findTokens(it) }
            .sum()

    private fun findTokens(bp: ButtonsAndPrize): Int? {
        val buttonASequence: List<Pair<Long, Long>> = generateSequence( 0L to 0L ) { it.first + bp.a.x to it.second + bp.a.y }
            .takeWhile { it.first < bp.prize.first || it.second < bp.prize.second }.toList()
        val buttonBSequence: List<Pair<Long, Long>> = generateSequence( 0L to 0L ) { it.first + bp.b.x to it.second + bp.b.y }
            .takeWhile { it.first < bp.prize.first || it.second < bp.prize.second }.toList()

        val foo = buttonASequence.mapIndexed { aPresses, (xA, yA) ->
            buttonBSequence.mapIndexedNotNull { bPresses, (xB, yB) ->
                if (xA + xB to yA + yB == bp.prize) {
                    aPresses to bPresses
                } else null
            }
        }.flatten()
            .takeIf { it.isNotEmpty() }
            ?.minOf { (a, b) -> (a * 3) + (b * 1) }
        return foo
    }

    private val additional = 10000000000000L
    fun solvePart2(): Long =
        buttonsAndPrizes
            // make ButtonsAndPrize with larger prize value, then turn it into the ClawMachine for easier understanding of matrix
            .map { ClawMachine(ButtonsAndPrize(a = it.a, b = it.b, prize = it.prize.first + additional to it.prize.second + additional)) }
            .sumOf { it.pressButtons() }

    private fun parse(input: List<String>): List<ButtonsAndPrize> {

        val buttonA = Regex("""Button A: X\+(\d+), Y\+(\d+)""")
        val buttonB = Regex("""Button B: X\+(\d+), Y\+(\d+)""")
        val prize = Regex("""Prize: X=(\d+), Y=(\d+)""")

        val buttonsAndPrizes = mutableListOf<ButtonsAndPrize>()
        var index = 0
        while (input.getOrNull(index) != null) {
            buttonsAndPrizes.add(
                ButtonsAndPrize(
                    a = buttonA.find(input[index])?.groupValues?.drop(1)?.let { (x, y) -> Point2D(x.toInt(), y.toInt()) } ?: throw Exception("Bad line ${input[index]}"),
                    b = buttonB.find(input[index+1])?.groupValues?.drop(1)?.let { (x, y) -> Point2D(x.toInt(), y.toInt()) } ?: throw Exception("Bad line ${input[index+1]}"),
                    prize = prize.find(input[index+2])?.groupValues?.drop(1)?.let { (x, y) -> x.toLong() to y.toLong() } ?: throw Exception("Bad line ${input[index+2]}")
                )
            )
            index += 4
        }
        return buttonsAndPrizes
    }

    class ButtonsAndPrize(
        val a: Point2D,
        val b: Point2D,
        val prize: Pair<Long, Long>
    )

    class ClawMachine(
        val aX: Long, val aY: Long,
        val bX: Long, val bY: Long,
        val prizeX: Long, val prizeY: Long
    ) {
        constructor(bp: ButtonsAndPrize) : this(
            aX = bp.a.x.toLong(), aY = bp.a.y.toLong(),
            bX = bp.b.x.toLong(), bY = bp.b.y.toLong(),
            prizeX = bp.prize.first, prizeY = bp.prize.second,
        )

        fun pressButtons(): Long {
            val det = aX * bY - aY * bX
            val a = (prizeX * bY - prizeY * bX) / det
            val b = (aX * prizeY - aY * prizeX) / det
            return if (aX * a + bX * b == prizeX && aY * a + bY * b == prizeY) {
                a * 3 + b
            } else 0
        }
    }

    private operator fun Point2D.times(num: Int) = Point2D(x * num, y * num)

}



