package test

import Resources.resourceAsListOfString
import aoc_2024.Day12
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 12")
class Day12Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example 1`() {
            val answer = Day12(resourceAsListOfString("day12_example1.txt")).solvePart1()

            assertThat(answer).isEqualTo(140)
        }

        @Test
        fun `Matches example 2`() {
            val answer = Day12(resourceAsListOfString("day12_example2.txt")).solvePart1()

            assertThat(answer).isEqualTo(772)
        }

        @Test
        fun `Matches example 3`() {
            val answer = Day12(resourceAsListOfString("day12_example3.txt")).solvePart1()

            assertThat(answer).isEqualTo(1930)
        }


        @Test
        fun `Actual answer`() {
            val answer = Day12(resourceAsListOfString("day12.txt")).solvePart1()

            assertThat(answer).isEqualTo(6288599492129)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example 1`() {
            val answer = Day12(resourceAsListOfString("day12_example1.txt")).solvePart2()

            assertThat(answer).isEqualTo(80)
        }

        @Test
        fun `Matches example 2`() {
            val answer = Day12(resourceAsListOfString("day12_example2.txt")).solvePart2()

            assertThat(answer).isEqualTo(436)
        }

        @Test
        fun `Matches example E`() {
            val answer = Day12(resourceAsListOfString("day12_E.txt")).solvePart2()

            assertThat(answer).isEqualTo(236)
        }

        @Test
        fun `Matches example AB`() {
            val answer = Day12(resourceAsListOfString("day12_AB.txt")).solvePart2()

            assertThat(answer).isEqualTo(368)
        }

        @Test
        fun `Matches example 3`() {
            val answer = Day12(resourceAsListOfString("day12_3.txt")).solvePart2()

            assertThat(answer).isEqualTo(1206)
        }


        @Test
        fun `Actual answer`() {
            val answer = Day12(resourceAsListOfString("day12.txt")).solvePart2()

            assertThat(answer).isEqualTo(855082)
        }

    }
}