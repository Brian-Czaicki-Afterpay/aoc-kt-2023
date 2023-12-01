fun main() {
    fun createIntFromStrings(string1: String, string2: String): Int { return (string1 + string2).toInt() }

    fun convertWordToDigit(wordNumberPair: Pair<Int, String>?): String {
        val wordNumber = wordNumberPair?.second ?: throw Exception("No word number found")

        return when (wordNumber) {
            "one" -> "1"
            "two" -> "2"
            "three" -> "3"
            "four" -> "4"
            "five" -> "5"
            "six" -> "6"
            "seven" -> "7"
            "eight" -> "8"
            "nine" -> "9"
            else -> wordNumber
        }
    }

    val numbers = (1..9).map { it.toString() }
    val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val wordsAndNumbers = numbers.toMutableList() + words

    fun part1(input: List<String>): Int {
        return input.sumOf {
            line -> createIntFromStrings(
                line.findAnyOf(numbers)!!.second,
                line.findLastAnyOf(numbers)!!.second
            )
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            line -> createIntFromStrings(
                convertWordToDigit(line.findAnyOf(wordsAndNumbers)),
                convertWordToDigit(line.findLastAnyOf(wordsAndNumbers))
            )
        }
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
