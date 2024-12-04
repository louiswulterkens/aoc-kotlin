import aoc_2023.aoc1_23.aoc1P1_23
import aoc_2023.aoc1_23.aoc1P2_23
import aoc_2023.aoc2_23.aoc2P1_23
import aoc_2023.aoc2_23.aoc2P2_23
import aoc_2024.aoc2_24.aoc2P1_24
import aoc_2024.aoc2_24.aoc2P2_24
import aoc_2024.aoc3_24.aoc3P1_24
import aoc_2024.aoc3_24.aoc3P2_24
import aoc_2024.aoc4_24.aoc4P1_24
import aoc_2024.aoc4_24.aoc4P2_24
import java.io.File
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    aoc2024()
}

fun aoc2023() {
    aoc1P1_23(input = fileName(problem = 1, year = 2023))
    aoc1P2_23(input = fileName(problem = 1, year = 2023))

    aoc2P1_23(input = fileName(problem = 2, year = 2023))
    aoc2P2_23(input = fileName(problem = 2, year = 2023))
}

fun aoc2024() {
//    aoc1P1_24(getFileAsString(fileName(problem = 1)))
//    aoc1P2_24(getFileAsString(fileName(problem = 1)))

//    aoc2P1_24(getFileAsString(fileName(problem = 2)))
//    aoc2P2_24(getFileAsString(fileName(problem = 2)))

//    aoc3P1_24(getFileAsString(fileName(problem = 3)))
//    aoc3P2_24(getFileAsString(fileName(problem = 3)))

//    aoc4P1_24(getFileAsString(fileName(problem = 4)))
    aoc4P2_24(getFileAsString(fileName(problem = 4)))

}

fun getFileAsStringLines(input: String): List<String> =
    File(input).let {
        if (it.exists()) it.readText().trim().split("\r\n+".toRegex()) else throw Exception("File does not exists: $input")
    }

fun getFileAsString(input: String): String =
    File(input).let {
        if (it.exists()) it.readText().trim() else throw Exception("File does not exists: $input")
    }

fun fileName(problem: Int, year: Int = 2024, test: Boolean = false) =
    "C:/Users/Skitt/Documents/GitHub/aoc-kotlin/src/aoc_$year/aoc${problem}_${year%2000}/${if (test) "test" else "input"}.txt"