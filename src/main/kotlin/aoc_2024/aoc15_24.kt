package aoc_2024

class Day15(val input: List<String>) {
    lateinit var warehouse: List<CharArray>
    lateinit var instructions: CharArray
    lateinit var start: Point2D

    fun solvePart1(): Int {
        p1Parse(input)
        var pos = start
        instructions.forEach { dir ->
            val pointDir = getDir(dir)
            if (pos.push(pointDir)) {
                pos += pointDir
            }
//            debugPrint(dir)
        }

        return warehouse.mapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == 'O') {
                    (100 * y) + x
                } else {
                    null
                }
            }.filterNotNull().sum()
        }.sum()
    }

    fun solvePart2(): Int {
        p2Parse(input)
        var pos = start
        debugPrint(' ')
        instructions.forEach { dir ->
            val pointDir = getDir(dir)
            if (pointDir == Point2D.WEST || pointDir == Point2D.EAST) {
                if (pos.push(pointDir)) {
                    pos += pointDir
                }
            } else {
                if (pos.pushLarge(pointDir)) {
                    pos.moveLarge(pointDir)
                    pos += pointDir
                }
            }
//            debugPrint(dir)
        }

        return warehouse.mapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == '[') {
                    (100 * y) + x
                } else {
                    null
                }
            }.filterNotNull().sum()
        }.sum()
    }

    private fun Point2D.push(direction: Point2D): Boolean {
        when (warehouse[this + direction]) {
            '#' -> return false
            'O', '[', ']' -> {
                if ((this + direction).push(direction)) {
                    this.move(this + direction)
                    return true
                } else {
                    return false
                }
            }
            else -> {
                this.move(this + direction)
                return true
            }
        }
    }

    private fun Point2D.move(new: Point2D) {
        warehouse[new] = warehouse[this]!!
        warehouse[this] = '.'
    }

    private fun Point2D.pushLarge(direction: Point2D): Boolean {
        when (warehouse[this + direction]) {
            '#' -> return false
            ']' -> {

                val left = this + direction + Point2D.WEST
                val right = this + direction
                return left.pushLarge(direction) && right.pushLarge(direction)
            }
            '[' -> {
                val left = this + direction
                val right = this + direction + Point2D.EAST
                return left.pushLarge(direction) && right.pushLarge(direction)
            }
            else -> {
                return true
            }
        }
    }

    private fun Point2D.moveLarge(dir: Point2D) {
        val new = this + dir
        if (warehouse[new] == '[') {
            (new).moveLarge(dir)
            (new + Point2D.EAST).moveLarge(dir)
        }

        if (warehouse[new] == ']') {
            (new).moveLarge(dir)
            (new + Point2D.WEST).moveLarge(dir)
        }
        warehouse[new] = warehouse[this]!!
        warehouse[this] = '.'
    }


    private fun findStart(): Point2D {
        var posXY = 0 to 0
        for (i in warehouse.indices) {
            warehouse[i].indexOf('@').takeIf { it != -1 }?.let { posXY = it to i }
        }
        return Point2D(posXY.first, posXY.second)
    }

    private fun debugPrint(instruct: Char) {
        println("Instruction: $instruct")
        warehouse.forEach {
            println(it)
        }
    }

    private fun getDir(c: Char): Point2D = when (c) {
        '^' -> Point2D.NORTH
        '>' -> Point2D.EAST
        'v' -> Point2D.SOUTH
        '<' -> Point2D.WEST
        else -> throw Exception("Unexpected Direction: $c")
    }

    private fun p1Parse(input: List<String>) {
        val split = input.indexOfFirst { it.isEmpty() }
        warehouse = input.subList(0, split).map { it.toCharArray() }
        instructions = input.subList(split+1, input.size).joinToString("").toCharArray()
        start = findStart()
    }

    private fun p2Parse(input: List<String>) {
        val split = input.indexOfFirst { it.isEmpty() }
        instructions = input.subList(split+1, input.size).joinToString("").toCharArray()
        warehouse = input.subList(0, split).map { it.toCharArray() }.map { row ->
            row.joinToString("") { c ->
                when (c) {
                    '#' -> "##"
                    'O' -> "[]"
                    '.' -> ".."
                    '@' -> "@."
                    else -> throw Exception("Unknown Character: $c")
                }
            }.toCharArray()
        }
        start = findStart()
    }
}



