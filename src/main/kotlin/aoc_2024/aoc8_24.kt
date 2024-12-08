package aoc_2024

class Day08(input: List<String>) {

    private val map = input.map { it.toCharArray() }

    private val antennas = antennaPositions()

    fun solvePart1(): Int =
        antennas.map { (_, aList) ->
            aList.pairUp()
                .map { (a, b) ->
                    val diff = b - a
                    listOf(a - diff, b + diff)
                }.flatten()
                .filter { map.safe(it) }
        }.flatten()
            .toSet().size

    fun solvePart2(): Int =
        antennas.map { (_, aList) ->
            aList.pairUp()
                .map { (a, b) ->
                    val diff = b - a

//                    Old Way: separately creating a List, Start, and adding to the list until it wasn't safe

//                    var posMinus = a - diff
//                    val lineMinus = mutableSetOf<Point2D>()
//                    while (map.safe(posMinus)) {
//                        lineMinus.add(posMinus).also { posMinus -= diff }
//                    }

                    // New Way:
                    val lineMinus = generateSequence(a - diff) { it-diff }
                        .takeWhile { map.safe(it) }.toList()
                    val linePlus = generateSequence(b + diff) { it + diff }
                        .takeWhile { map.safe(it) }.toList()
                    lineMinus + linePlus + listOf(a, b)
                }.flatten()
                .filter { map.safe(it) }
        }.flatten()
            .toSet().size

    private fun <T> List<T>.pairUp() = this.mapIndexed { i, x ->
        this.slice(i + 1..<size).map { x to it }
    }.flatten()

    private fun antennaPositions(): Map<Char, List<Point2D>> =
        map.flatMapIndexed { y, row ->
                row.mapIndexed { x, c ->
                    if (c != '.') Point2D(x, y) else null
                }
            }
            .filterNotNull()
            .groupBy { map[it]!! }
            
}