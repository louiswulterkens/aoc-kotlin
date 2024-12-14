package test

import Resources.resourceAsListOfString
import aoc_2024.Day14
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 14")
class Day14Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example 1`() {
            val answer = Day14(resourceAsListOfString("day14_example.txt")).solvePart1(true)

            assertThat(answer).isEqualTo(12)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day14(resourceAsListOfString("day14.txt")).solvePart1()

            assertThat(answer).isEqualTo(231852216)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example 1`() {
            val answer = Day14(resourceAsListOfString("day14_example.txt")).solvePart2()

            assertThat(answer).isEqualTo(875318608908L)
        }

        @Test
        fun `Matches example 3`() {
            val answer = Day14(resourceAsListOfString("day14.txt")).solvePart2()

            assertThat(answer).isEqualTo(102718967795500L)
        }
    }
}