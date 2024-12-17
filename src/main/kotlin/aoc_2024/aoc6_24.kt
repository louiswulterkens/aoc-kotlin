package aoc_2024

class Day06(val input: List<String>) {

    val start = findStart(input)

    fun solvePart1(): Int {
        val uniqueSpots = mutableSetOf<Pair<Int, Int>>()
        var directionIndex = 0

        var posXY = findStart(input)

        while (input.safeAt(posXY.first, posXY.second) != ' ') {
            uniqueSpots.add(posXY)

            DIRECTIONS.indices.first { i ->
                val dir = (directionIndex + i) % DIRECTIONS.size
                DIRECTIONS[dir].let { (moveX, moveY) ->
                    if (!input.isBlocked(posXY.first + moveX, posXY.second + moveY)) {
                        posXY = posXY.first + moveX to posXY.second + moveY
                        directionIndex = (directionIndex + i) % DIRECTIONS.size
                        true
                    } else false
                }
            }
        }

        return uniqueSpots.size
    }

    fun solvePart2(): Int {
        val traversed = mutableSetOf<Triple<Int, Int, Int>>()
        val blockers = mutableSetOf<Pair<Int, Int>>()
        var posXY = findStart(input)

        var directionIndex = 0

        //initialize
        var tmpPosXY = findStart(input)
        while (input.safeAt(tmpPosXY.first, tmpPosXY.second) != ' ') {
            traversed.add(tmpPosXY.let { (x, y) -> (Triple(x, y, 0)) })
            DIRECTIONS[2].let { (moveX, moveY) -> // move down
                if (!input.isBlocked(posXY.first + moveX, posXY.second + moveY)) {
                    tmpPosXY = tmpPosXY.first + moveX to tmpPosXY.second + moveY
                    directionIndex = (directionIndex + 0) % DIRECTIONS.size // set direction up
                    true
                } else false
            }
        }

        //traverse
        while (input.safeAt(posXY.first, posXY.second) != ' ') {

            DIRECTIONS.indices.first { i ->
                val dir = (directionIndex + i) % DIRECTIONS.size
                DIRECTIONS[dir].let { (moveX, moveY) ->
                    if (!input.isBlocked(posXY.first + moveX, posXY.second + moveY)) {
                        //assign new direction
                        directionIndex = (directionIndex + i) % DIRECTIONS.size
                        //add position with new direction
                        traversed.add(posXY.let { (x, y) -> (Triple(x, y, directionIndex)) })
                        //move to new position
                        posXY = posXY.first + moveX to posXY.second + moveY
                        true
                    } else false
                }
            }
        }

        traversed.forEach { (x, y, dir) ->
            val loopDir = (dir+1) % DIRECTIONS.size
            val loopTriple = DIRECTIONS[loopDir].let { (moveX, moveY) ->
                val loopX = x + moveX
                val loopY = y + moveY

                Triple(loopX, loopY, loopDir)
            }
            if (traversed.contains(loopTriple)) {
                println("Looped Triple = $loopTriple")
                blockers.add(loopTriple.first to loopTriple.second)
            }
        }

        return blockers.size
    }

    private val tmp = listOf<Triple<Int, Int, Int>>(
    Triple(4, 5, 0),
    Triple(5, 6, 3),
    Triple(6, 8, 2),
    Triple(2, 7, 0),
    Triple(4, 7, 0),
    Triple(6, 8, 3)
    )

    private companion object {
        val DIRECTIONS = listOf(
            0 to -1, // Up
            1 to 0,  // Right
            0 to 1,  // Down
            -1 to 0  // Left
        )

        private fun findStart(input: List<String>): Pair<Int, Int> {
            var posXY = 0 to 0
            for (i in input.indices) {
                input[i].indexOf('^').takeIf { it != -1 }?.let { posXY = it to i }
            }
            return posXY
        }
    }

    private fun List<String>.safeAt(x: Int, y: Int): Char =
        if(y in this.indices && x in this[y].indices) this[y][x] else ' '

    private fun List<String>.isBlocked(x: Int, y: Int): Boolean =
        this.safeAt(x, y) == '#'
}



