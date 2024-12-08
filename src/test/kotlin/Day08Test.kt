package test

import Resources.resourceAsListOfString
import aoc_2024.Day06
import aoc_2024.Day08
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 8")
class Day08Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            val answer = Day08(resourceAsListOfString("day08_example.txt")).solvePart1()

            assertThat(answer).isEqualTo(14)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day08(resourceAsListOfString("day08.txt")).solvePart1()

            assertThat(answer).isEqualTo(269)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches first example`() {
            val answer = Day08(resourceAsListOfString("day08_example2.txt")).solvePart2()

            assertThat(answer).isEqualTo(9)
        }

        @Test
        fun `Matches second example`() {
            val answer = Day08(resourceAsListOfString("day08_example.txt")).solvePart2()

            assertThat(answer).isEqualTo(34)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day08(resourceAsListOfString("day08.txt")).solvePart2()

            assertThat(answer).isEqualTo(949)
        }

    }
}