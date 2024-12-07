package main.kotlin.aoc_2024.aoc4_24

import main.kotlin.aoc_2023.aoc1_23.indexesOf

fun aoc4P1_24(input: String) {
    var directional = directionalStrings(input.split("\r\n").map { it.toCharArray().toList() })
    directional = directional.filter { it.length >= 4 }
    val totals = directional.map { Regex("""(?=(XMAS|SAMX))""").findAll(it).map { it.groupValues[1] }.count() }
    println("Total: ${totals.sum()}")
}

private fun directionalStrings(grid: List<List<Char>>): List<String> {
    val result = mutableListOf<String>()
    val numRows = grid.size
    val numColumns = grid[0].size
    // Rows
    result.addAll(grid.map { row -> row.joinToString("") } )
    // Columns

    result.addAll(
        (0 .. numColumns-1).map { column ->
            grid.map { row -> row[column] }.joinToString("")
        }
    )
    // Diagonal: \
    result.addAll(
        (-numRows..numColumns -1).map { diagonal ->
            grid.mapIndexedNotNull { index, row ->
                row.getOrNull(diagonal + index)
            }.joinToString("")
        }.reversed()
    )
    // Diagonal: /
    result.addAll(
        (0..numColumns + numRows -1).map { diagonal ->
            grid.mapIndexedNotNull { index, row ->
                row.getOrNull(diagonal - index)
            }.joinToString("")
        }
    )
    return result
}


fun aoc4P2_24(input: String) {
    val grid = input.split("\r\n").map { it.toCharArray().toList() }
    val total = input.split("\r\n").mapIndexed { y, line ->
        line.indexesOf("A").sumOf { x ->
            checkTheCross(grid, x, y)
        }
    }.sum()

    println("Total: $total")
}

fun checkTheCross(grid: List<List<Char>>, x: Int, y: Int): Int {
    // Top left, Clockwise
    val diagonals: List<Char> =  try { listOf(grid[y-1][x-1], grid[y-1][x+1], grid[y+1][x+1], grid[y+1][x-1]) } catch (e: Exception) { return 0 }
    // Top, Clockwise
//    val cardinals: List<Char> =  try { listOf(grid[y-1][x], grid[y][x+1], grid[y+1][x], grid[y][x-1]) } catch (e: Exception) { return 0 }

    val diagonalX = diagonals.filterIndexed { index, c ->
        (c == 'M' || c == 'S') &&
                ((diagonals[index] == diagonals[(index+1)%4])xor(diagonals[index] == diagonals[((index-1)+4/*Keep it Positive*/)%4]))
    }.size == 4

//    val cardinalX = cardinals.filterIndexed { index, c ->
//        (c == 'M' || c == 'S') &&
//                ((cardinals[index] == cardinals[(index+1)%4])xor(cardinals[index] == cardinals[((index-1)+4/*Keep it Positive*/)%4]))
//    }.size == 4

    return listOf(diagonalX).count { it }

    /**
        X's in the Cardinal Plane do not count -_- I got baited by Reddit + The Example and wrote both of them simultaneously instead
        of just the Diagonal one first.
     */
}

