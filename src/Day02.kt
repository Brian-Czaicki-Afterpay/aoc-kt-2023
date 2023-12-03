fun main() {
    val colorValueMap= mutableMapOf<String, Int>("red" to 12, "green" to 13, "blue" to 14)

    fun checkGame(game: String): Boolean {
        return game
            .split(",")
            .all { colorNumberValue ->
                val value = colorNumberValue.filter { it.isDigit() }
                val color = colorNumberValue.substringAfter(value).trim()
                colorValueMap[color]!! >= value.toInt()
            }
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val number = line.substringAfter(" ").substringBefore(":")
            val games = line.substringAfter(": ").split("; ")
            if (games.all { game -> checkGame(game) }) number.toInt()
            else 0
        }
    }

    val initialColorMap = mutableMapOf<String, Int>("red" to 0, "green" to 0, "blue" to 0)

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val games = line.substringAfter(": ").split("; ")
            val gameColorMap = initialColorMap.toMutableMap()

            games.map { game ->
                val colorNumberValues = game.split(",")
                colorNumberValues.map { colorNumberValue ->
                    val value = colorNumberValue.filter { it.isDigit() }
                    val color = colorNumberValue.substringAfter(value).trim()
                    if(gameColorMap[color]!! < value.toInt()) gameColorMap[color] = value.toInt()
                }
            }
            gameColorMap
                .values
                .fold(1) {acc, i -> acc * i  }.toInt()
        }
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}