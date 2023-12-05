import kotlin.math.pow

fun main() {
    fun String.grabIntsFromString(): List<Int> {
        return this
            .split(" ")
            .filter { it != "" }
            .map { it.toInt() }
            .toList()
    }

    fun getWinningNumbers (line: String): List<Int> {
        return line
            .substringAfter(": ")
            .substringBefore(" |")
            .grabIntsFromString()
    }

    fun getYourNumbers (line: String): List<Int> {
        return line.substringAfter("| ").grabIntsFromString()
    }

    fun getMatches (winningNums: List<Int>, yourNumbers: List<Int>): Int {
        return winningNums.intersect(yourNumbers.toSet()).count()
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val matches = getMatches(getWinningNumbers(line), getYourNumbers(line))
            2.toDouble().pow(matches - 1).toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val totalCardsHashMap = List(input.size) { index -> index to 1 }.toMap(mutableMapOf())

        input.map { line ->
            val matches = getMatches(getWinningNumbers(line), getYourNumbers(line))
            val cardVal = line
                .substringBefore(":")
                .filter { it.isDigit() }
                .toInt() - 1

            repeat(totalCardsHashMap[cardVal]!!) {
                repeat(matches) {index ->
                    totalCardsHashMap[cardVal + index + 1] = totalCardsHashMap[cardVal + index + 1]!! + 1
                }
            }
        }
        return totalCardsHashMap.values.sum()
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}