import aoc_2023.aoc2.aoc2P2
import java.io.File


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val basePath = "C:/Users/Skitt/Documents/GitHub/aoc-kotlin/src"

//    aoc1P1("$basePath/aoc_2023.aoc1/input.txt")
//    aoc1P2("$basePath/aoc_2023.aoc1/input.txt")

//    aoc2P1(input = "$basePath/aoc_2023.aoc2/input.txt")
    aoc2P2(input = "$basePath/aoc_2023/aoc2/input.txt")
}

fun getFileAsString(input: String): String = File(input).readText().trim()