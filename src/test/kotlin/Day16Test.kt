package test

import Resources.resourceAsListOfString
import aoc_2024.Day16
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 16")
class Day16Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            val answer = Day16(resourceAsListOfString("day16_example.txt")).solvePart1()

            assertThat(answer).isEqualTo(7036)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day16(resourceAsListOfString("day16.txt")).solvePart1()

            assertThat(answer).isEqualTo(104516)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            val answer = Day16(resourceAsListOfString("day16_example.txt")).solvePart2()

            assertThat(answer).isEqualTo(9021)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day16(resourceAsListOfString("day16.txt")).solvePart2()

            assertThat(answer).isEqualTo(1468005)
        }
    }
}