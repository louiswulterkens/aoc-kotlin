package test

import Resources.resourceAsListOfString
import aoc_2024.Day06
import aoc_2024.Day06Redo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 6")
class Day06Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day06Redo(resourceAsListOfString("day06_example.txt")).solvePart1()

            assertThat(answer).isEqualTo(41)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day06Redo(resourceAsListOfString("day06.txt")).solvePart1()

            assertThat(answer).isEqualTo(5208)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day06Redo(resourceAsListOfString("day06_example.txt")).solvePart2()

            assertThat(answer).isEqualTo(6)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day06Redo(resourceAsListOfString("day06.txt")).solvePart2()

            assertThat(answer).isEqualTo(1972)
        }

    }
}