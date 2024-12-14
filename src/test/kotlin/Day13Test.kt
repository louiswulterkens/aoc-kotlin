package test

import Resources.resourceAsListOfString
import aoc_2024.Day13
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 13")
class Day13Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example 1`() {
            val answer = Day13(resourceAsListOfString("day13_example.txt")).solvePart1()

            assertThat(answer).isEqualTo(480)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day13(resourceAsListOfString("day13.txt")).solvePart1()

            assertThat(answer).isEqualTo(28753)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example 1`() {
            val answer = Day13(resourceAsListOfString("day13_example.txt")).solvePart2()

            assertThat(answer).isEqualTo(875318608908L)
        }

        @Test
        fun `Matches example 3`() {
            val answer = Day13(resourceAsListOfString("day13.txt")).solvePart2()

            assertThat(answer).isEqualTo(102718967795500L)
        }
    }
}