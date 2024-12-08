package aoc_2024

operator fun List<CharArray>.get(point: Point2D): Char? =
    getOrNull(point.y)?.getOrNull(point.x)

operator fun List<CharArray>.set(point: Point2D, char: Char) {
    this[point.y][point.x] = char
}

fun List<CharArray>.safe(point: Point2D): Boolean =
    point.let { (x, y) -> y in this.indices && x in this[y].indices }
