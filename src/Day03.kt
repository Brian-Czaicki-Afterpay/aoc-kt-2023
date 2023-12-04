fun main() {

    fun touchesSymbol (yCoord: Int, xCoordStart: Int, xCoordEnd: Int, symbols: List<Pair<Int, Int>>): Boolean {
        return symbols.any { (x, y) ->
            x in (xCoordStart- 1)..(xCoordEnd + 1)
                    && y in (yCoord - 1)..(yCoord + 1)
        }
    }

    fun calculateAsterisk (yCoord: Int, xCoord: Int, numbers: List<Pair<String, Pair<Int, Int>>>): Int {
        val asteriskTouches = numbers.filter {
            val numberYCoord = it.second.second
            val numberXCoord = it.second.first
            val numberLength = it.first.length

            xCoord in (numberXCoord - 1)..(numberXCoord + numberLength)
                    && yCoord in (numberYCoord - 1)..(numberYCoord + 1)
        }

        return if(asteriskTouches.count() == 2) {
            asteriskTouches.fold(1) { acc, i -> acc * i.first.toInt() }.toInt()
        }
        else {
            0
        }

    }

    fun getSymbols(input: List<String>): List<Pair<Int, Int>> {
        val symbolRegex = Regex("[^0-9.]")
        return input
            .mapIndexed { yCoord, it -> symbolRegex.findAll(it).map { Pair(it.range.first, yCoord) }.toList() }
            .toList()
            .flatten()
    }

    fun getAsterisks(input: List<String>): List<Pair<Int, Int>> {
        val asteriskRegex = Regex("[*]")
        return input
            .mapIndexed { yCoord, it -> asteriskRegex.findAll(it).map { Pair(it.range.first, yCoord) }.toList() }
            .toList()
            .flatten()
    }

    fun getNumsWithIndx(input: List<String>): List<Pair<String, Pair<Int, Int>>> {
        val numberOnlyRegex = Regex("[0-9]+")
        return input
            .mapIndexed { yCoord, it -> numberOnlyRegex.findAll(it).map { Pair(it.value, Pair(it.range.first, yCoord)) }.toList() }
            .toList()
            .flatten()
    }

    fun part1(input: List<String>): Int {
        val symbols = getSymbols(input)
        val numbersWithIndex = getNumsWithIndx(input)

        return numbersWithIndex
            .filter { touchesSymbol(it.second.second, it.second.first, it.second.first + it.first.length - 1, symbols )}
            .map { it.first.toInt()}
            .toList()
            .sum()
    }

    fun part2(input: List<String>): Int {
        val asterisks = getAsterisks(input)
        val numbers = getNumsWithIndx(input)

        println(asterisks)
        println(numbers)

        return asterisks.sumOf { calculateAsterisk(it.second, it.first, numbers) }
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}