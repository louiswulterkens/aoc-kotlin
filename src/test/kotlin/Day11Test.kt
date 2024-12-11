package test

import Resources.resourceAsListOfInt
import Resources.resourceAsListOfString
import Resources.resourceAsString
import aoc_2024.Day11
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 11")
class Day11Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = Day11(resourceAsString("day11_example.txt"), 6).solvePart1()

            assertThat(answer).isEqualTo(22)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day11(resourceAsString("day11.txt"), 25).solvePart1()

            assertThat(answer).isEqualTo(193269)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day11(resourceAsString("day11_example.txt"), 75).solvePart2()

            assertThat(answer).isEqualTo(65601038650482L)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day11(resourceAsString("day11.txt"), 75).solvePart2()

            assertThat(answer).isEqualTo(228449040027793)
        }

    }
}