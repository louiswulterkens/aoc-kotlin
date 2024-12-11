package aoc_2024

class Day09(val input: String) {

    private val resource: List<Int> = input.map { it.code - '0'.code }

    fun solvePart1(): Long {

        val expanded: MutableList<String> = format().flatten().toMutableList()

        var i = 0
        while(i in expanded.indices) {
            if (expanded[i] == ".") {
                expanded.apply {
                    this.removeLastWhile { it == "." }
                    if (i < lastIndex) {
                        set(i, this[lastIndex])
                        removeAt(lastIndex)
                    }
                }
            }
            i++
        }

        return expanded.mapIndexed { index, value ->
            index * value.toLong()
        }.sum()
    }

    fun solvePart2(): Long {
        val expanded = format().filter { it.isNotEmpty() }.toMutableList()

        var i = expanded.size-1
        while (i in expanded.indices) {
            if (expanded[i][0] != ".") {
                val indexOfGap = expanded.subList(0, i).indexOfFirst { it.size >= expanded[i].size && it[0] == "." }
                if (indexOfGap > 0) {
                    val moving = expanded.removeAt(i).also { expanded.add(i, List(it.size) { "." }) }
                    val gap = expanded[indexOfGap]
                    expanded.apply {
                        add(indexOfGap, moving)
                        gap.subList(moving.size, gap.size).takeIf { it.isNotEmpty() }?.let {
                            add(indexOfGap+1, it)
                            removeAt(indexOfGap+2)
                            i++
                        } ?: removeAt(indexOfGap+1)
                    }
                }
            }
            i--
        }

        return expanded.flatten().mapIndexedNotNull { index, value ->
            when (value) {
                "." -> null
                else -> index * value.toLong()
            }
        }.sum()
    }

    private fun Int.isEven() = this % 2 == 0

    private fun MutableList<String>.removeLastWhile(predicate: (String) -> Boolean): Boolean {
        while (this.size > 0 && predicate(this[lastIndex])) {
            removeAt(lastIndex)
        }
        return true
    }
    private fun MutableList<List<String>>.removeLastListWhile(predicate: (String) -> Boolean): Boolean {
        while (this.size > 0 && predicate(this[lastIndex][0])) {
            removeAt(lastIndex)
        }
        return true
    }

    private fun format() =
        resource.mapIndexed { index, value ->
            List(value) {
                if (index.isEven()) (index/2).toString() else "."
            }
        }
}