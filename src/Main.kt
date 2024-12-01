import aoc_2023.aoc1_23.aoc1P1_23
import aoc_2023.aoc1_23.aoc1P2_23
import aoc_2023.aoc2_23.aoc2P1_23
import aoc_2023.aoc2_23.aoc2P2_23
import aoc_2024.aoc1_24.aocP1_24
import aoc_2024.aoc1_24.aocP2_24
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
    aocP1_24(getFileAsString(fileName(problem = 1)))
    aocP2_24(getFileAsString(fileName(problem = 1)))
}

fun getFileAsString(input: String): List<String> =
    File(input).let{
        if (it.exists()) it.readText().trim().split("\n+".toRegex()) else throw Exception("File does not exists: $input")
    }

fun fileName(problem: Int, year: Int = 2024, test: Boolean = false) =
    "C:/Users/Skitt/Documents/GitHub/aoc-kotlin/src/aoc_$year/aoc${problem}_${year%2000}/${if (test) "test" else "input"}.txt"