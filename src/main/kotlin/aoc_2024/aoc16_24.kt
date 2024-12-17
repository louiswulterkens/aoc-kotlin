package aoc_2024

import java.util.PriorityQueue

/*
    This is a copied solution from https://todd.ginsberg.com/post/advent-of-code/2024/day16/

    I looked up and originally tried Dijkstra's, but through a combination of poorly handling directions (T-intersections kept not incrementing by 1,000)
    not understanding/leveraging Priority Queue's, and not understanding fully my end conditions because of my lack of Priority Queue, I got stuck being off
    by factors of 1,000. I read/understood this solution, and I'm hoping another path-finding algorithm problem comes up so that I can try and put my
    understanding to the test.
 */

class Day16(input: List<String>) {

    private val maze = input.map { it.toCharArray() }
    private val start: Point2D = findStart()
    private val end: Point2D = findEnd()

    // node == Point2D(9, 7) && dir == Point2D.EAST
    fun solvePart1(): Int = traverseMaze()

    private fun traverseMaze(): Int {
        val queue = PriorityQueue<Pair<Location, Int>>(compareBy { it.second })
            .apply { add(Location(listOf(start), Point2D.EAST) to 0) }
        val seen = mutableMapOf<Pair<Point2D, Point2D>, Int>()
        while (queue.isNotEmpty()) {
            val (location, cost) = queue.poll()
            if (location.position() == end) {
                return cost
            } else if (seen.getOrDefault(location.key(), Int.MAX_VALUE) > cost) {
                seen[location.key()] = cost
                location.step().apply {
                    if (maze[position()] != '#') {
                        queue.add(this to cost + 1)
                    }
                }
                queue.add(location.clockwise() to cost + 1000)
                queue.add(location.counterClockwise() to cost + 1000)
            }
        }
        throw Exception("No solution found")
    }

    fun solvePart2(): Int = traverseMazeWithPath()

    private fun traverseMazeWithPath(): Int {
        val queue = PriorityQueue<Pair<Location, Int>>(compareBy { it.second })
            .apply { add(Location(listOf(start), Point2D.EAST) to 0) }
        val seen = mutableMapOf<Pair<Point2D, Point2D>, Int>()
        var costAtGoal: Int? = null
        val allSpotsInAllPaths: MutableSet<Point2D> = mutableSetOf()

        while (queue.isNotEmpty()) {
            val (location, cost) = queue.poll()

            if(costAtGoal != null && cost > costAtGoal) {
                return allSpotsInAllPaths.size
            } else if (location.position() == end) {
                costAtGoal = cost
                allSpotsInAllPaths.addAll(location.positions)
            } else if (seen.getOrDefault(location.key(), Int.MAX_VALUE) >= cost) {
                seen[location.key()] = cost
                location.step().apply {
                    if (maze[position()] != '#') {
                        queue.add(this to cost + 1)
                    }
                }
                queue.add(location.clockwise() to cost + 1000)
                queue.add(location.counterClockwise() to cost + 1000)
            }
        }
        return allSpotsInAllPaths.size
    }

    private data class Location(
        val positions: List<Point2D>,
        val direction: Point2D
    ) {
        fun position(): Point2D = positions.last()
        fun key(): Pair<Point2D, Point2D> = position() to direction
        fun step(): Location = copy(positions = positions + (position() + direction))

        fun clockwise(): Location =
            copy(
                direction = when (direction) {
                    Point2D.NORTH -> Point2D.EAST
                    Point2D.EAST -> Point2D.SOUTH
                    Point2D.SOUTH -> Point2D.WEST
                    Point2D.WEST -> Point2D.NORTH
                    else -> throw IllegalStateException("Invalid turning point: $this")
                }
            )

        fun counterClockwise(): Location =
            copy(
                direction = when (direction) {
                    Point2D.NORTH -> Point2D.WEST
                    Point2D.WEST -> Point2D.SOUTH
                    Point2D.SOUTH -> Point2D.EAST
                    Point2D.EAST -> Point2D.NORTH
                    else -> throw IllegalStateException("Invalid turning point: $this")
                }
            )
    }

    private fun findStart(): Point2D {
        for (i in maze.indices) {
            maze[i].indexOf('S').takeIf { it != -1 }?.let { return Point2D(it, i) }
        }
        throw Exception("No Start Found")
    }
    private fun findEnd(): Point2D {
        for (i in maze.indices) {
            maze[i].indexOf('E').takeIf { it != -1 }?.let { return Point2D(it, i) }
        }
        throw Exception("No Start Found")
    }

    private fun Point2D.inverse() = Point2D(x * -1, y * -1)
    private fun Point2D.isSafe() = maze[y][x] != '#'
}



