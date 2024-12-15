package test

import Resources.resourceAsListOfString
import aoc_2024.Day15
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 15")
class Day15Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example short`() {
            val answer = Day15(resourceAsListOfString("day15_example_short.txt")).solvePart1()

            assertThat(answer).isEqualTo(2028)
        }

        @Test
        fun `Matches example`() {
            val answer = Day15(resourceAsListOfString("day15_example.txt")).solvePart1()

            assertThat(answer).isEqualTo(10092)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day15(resourceAsListOfString("day15.txt")).solvePart1()

            assertThat(answer).isEqualTo(1476771)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example p2`() {
            val answer = Day15(resourceAsListOfString("day15_example_p2.txt")).solvePart2()

            // Print and compare to AoC P2 Example
            //assertThat(answer).isEqualTo(2028)
        }

        @Test
        fun `Matches example`() {
            val answer = Day15(resourceAsListOfString("day15_example.txt")).solvePart2()

            assertThat(answer).isEqualTo(9021)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day15(resourceAsListOfString("day15.txt")).solvePart2()

            assertThat(answer).isEqualTo(1468005)
        }
    }
}