package test

import Resources.resourceAsListOfString
import aoc_2024.Day06
import aoc_2024.Day07
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 7")
class Day07Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day07(resourceAsListOfString("day07_example.txt")).solvePart1()

            assertThat(answer).isEqualTo(3749L)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day07(resourceAsListOfString("day07.txt")).solvePart1()

            assertThat(answer).isEqualTo(3245122495150L)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day07(resourceAsListOfString("day07_example.txt")).solvePart2()

            assertThat(answer).isEqualTo(11387)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day07(resourceAsListOfString("day07.txt")).solvePart2()

            assertThat(answer).isEqualTo(105517128211543)
        }

    }
}