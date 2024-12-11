package aoc_2024

data class Point2D(val x: Int, val y: Int) {

    operator fun plus(other: Point2D): Point2D =
        Point2D(x + other.x, y + other.y)

    operator fun minus(other: Point2D): Point2D =
        Point2D(x - other.x, y - other.y)

    operator fun compareTo(other: Point2D): Int =
        (x + y) - (other.x + other.y)

    companion object {
        val NORTH = Point2D(0, -1)
        val EAST = Point2D(1, 0)
        val SOUTH = Point2D(0, 1)
        val WEST = Point2D(-1, 0)

        val directions = listOf(NORTH, EAST, SOUTH, WEST)
    }

}

operator fun <T> List<List<T>>.get(point: Point2D): T? =
    this.getOrNull(point.y)?.getOrNull(point.x)