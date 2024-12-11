package test

import Resources.resourceAsString
import aoc_2024.Day09
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 9")
class Day09Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `POC summing logic`() {
            val input = listOf(0,0,9,9,8,1,1,1,8,8,8,2,7,7,7,3,3,3,6,4,4,6,5,5,5,5,6,6)
            val answer = input.mapIndexed {  index, i ->
                index * i
            }.sum()
            assertThat(answer).isEqualTo(1928)
        }

        @Test
        fun `Matches example`() {
            val answer = Day09(resourceAsString("day09_example.txt")).solvePart1()

            assertThat(answer).isEqualTo(1928)
        }



        @Test
        fun `Actual answer`() {
            val answer = Day09(resourceAsString("day09.txt")).solvePart1()

            assertThat(answer).isEqualTo(6288599492129)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            val answer = Day09(resourceAsString("day09_example.txt")).solvePart2()

            assertThat(answer).isEqualTo(2858)
        }

        @Test
        fun `Actual answer`() {
            val answer = Day09(resourceAsString("day09.txt")).solvePart2()

            assertThat(answer).isEqualTo(6321896265143)
        }

    }
}