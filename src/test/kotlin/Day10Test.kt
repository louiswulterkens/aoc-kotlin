package test

import Resources.resourceAsListOfString
import Resources.resourceAsString
import aoc_2024.Day09
import aoc_2024.Day10
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 10")
class Day10Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = Day10(resourceAsListOfString("day10_example.txt")).solvePart1()

            assertThat(answer).isEqualTo(36)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day10(resourceAsListOfString("day10.txt")).solvePart1()

            assertThat(answer).isEqualTo(744)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day10(resourceAsListOfString("day10_example.txt")).solvePart2()

            assertThat(answer).isEqualTo(81)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day10(resourceAsListOfString("day10.txt")).solvePart2()

            assertThat(answer).isEqualTo(1651)
        }

    }
}