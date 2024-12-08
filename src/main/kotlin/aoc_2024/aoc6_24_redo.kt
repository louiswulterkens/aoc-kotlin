package aoc_2024


/**
   This was essentially copied from here: https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day06.kt

   My first solution would check the "turn right" block of every space, with the assumption that if it saw one you could start a loop by turning at that point.

   While those were definitely loops, they weren't nearly all of them. The biggest flaw was that blocks at different positions could cause loops to occur in spaces that
   I'd never even touched, so my algorithm wouldn't find barely any loops. You truly do have to put the block at each spot, and then traverse out of the grid in order to
   know if you were in a loop or not. This felt like brute forcing but in reality it's the only way.

 */
class Day06Redo(val input: List<String>) {

    private var grid: List<CharArray> = input.map { it.toCharArray() }

    private val start = grid
        .flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == '^') Point2D(x, y) else null
            }
        }.filterNotNull().firstOrNull() ?: throw IllegalArgumentException("No Start Found")

    fun solvePart1(): Int =
        traverse().size

    fun traverse(): Set<Point2D> {
        val traversed = mutableSetOf<Point2D>()
        var location = start
        var direction = Point2D.NORTH

        while (grid[location] != null) {
            traversed.add(location)
            val next = location + direction
            if (grid[next] == '#') {
                direction = direction.turn()
            } else {
                location = next
            }
        }

        return traversed
    }

    fun solvePart2(): Int =
        traverse()
            .filterNot { it == start }
            .count { differentBlocker ->
                // Set new blocker
                grid[differentBlocker] = '#'
                traverseWithDirection().also {
                    // Reset: Remove the Blocker
                    grid[differentBlocker] = '.'
                }.second
            }

    fun traverseWithDirection(): Pair<Set<Point2D>, Boolean> {
        val traversed = mutableSetOf<Pair<Point2D, Point2D>>()
        var location = start
        var direction = Point2D.NORTH

        while (grid[location] != null && (location to direction) !in traversed) {
            traversed += location to direction
            val next = location + direction

            if (grid[next] == '#') {
                direction = direction.turn()
            } else {
                location = next
            }
        }

        return traversed.map { it.first }.toSet() to (grid[location] != null)
    }

    private fun Point2D.turn() : Point2D =
        when(this) {
            Point2D.NORTH -> Point2D.EAST
            Point2D.EAST -> Point2D.SOUTH
            Point2D.SOUTH -> Point2D.WEST
            Point2D.WEST -> Point2D.NORTH
            else -> throw IllegalArgumentException("Invalid direction $this")
        }
}
