package aoc_2023.aoc2

import java.io.File
import kotlin.math.max

val colorList: List<Pair<String, Int>> = listOf(Pair("red", 12), Pair("green", 13), Pair("blue", 14))

fun aoc2P1(input: String) {
    println("Input Name: $input")
    val file = File(input)

    if (file.exists()) {
        val fileRows = file.readText().split("\n+".toRegex())

        var total = 0

        fileRows.forEach { row ->
            val game: String
            val remainder: String
            row.split(":").let {
                game = it[0]
                remainder = it[1]
            }

            val gameNumber = Regex("""Game (\d+)""").find(game)?.groupValues?.get(1)?.toInt() ?: throw Exception("Didn't find a Game Number")
            println("gameNumber: $gameNumber")

            val brokenLimit = remainder.split(";").any { setOfCubes ->
                colorList.any { color ->
                    val number: Int? = Regex("""(\d+) ${color.first}""").find(setOfCubes)?.groupValues?.get(1)?.toInt()
                    number != null && number > color.second
                }
            }

            if (!brokenLimit) total += gameNumber
        }
        println("Total: $total")
    } else {
        println("File does not exist!")
    }
}

fun aoc2P2(input: String) {
    println("Input Name: $input")
    val file = File(input)

    if (file.exists()) {
        val fileRows = file.readText().split("\n+".toRegex())

        var total = 0

        fileRows.forEach { row ->
            var maxRed = 0
            var maxGreen = 0
            var maxBlue = 0

            val game: String
            val remainder: String
            row.split(":").let {
                game = it[0]
                remainder = it[1]
            }

            val gameNumber = Regex("""Game (\d+)""").find(game)?.groupValues?.get(1)?.toInt() ?: throw Exception("Didn't find a Game Number")
            println("gameNumber: $gameNumber")

            remainder.split(";").forEach { setOfCubes ->
                colorList.forEach { color ->
                    val number: Int? = Regex("""(\d+) ${color.first}""").find(setOfCubes)?.groupValues?.get(1)?.toInt()

                    number?.let {
                        when (color.first) {
                            "red" -> maxRed = max(it, maxRed)
                            "green" -> maxGreen = max(it, maxGreen)
                            "blue" -> maxBlue = max(it, maxBlue)
                        }
                    }
                }
            }
            total += maxRed * maxGreen * maxBlue
        }
        println("Total: $total")
    } else {
        println("File does not exist!")
    }
}
