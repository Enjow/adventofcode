import java.io.BufferedReader
import java.io.FileReader
import java.util.*
import kotlin.collections.HashMap

private const val FILENAME10 = "day10.txt"


fun main(args: Array<String>) {
    var gridHash = getInput(FILENAME10)
    var grid = gridHash.toSortedMap(compareBy<Position2D> { it.y }.thenBy { it.x })

    var gridb = HashMap<Position2D, List<Position2D>>()

    for ((currPos, currValue) in grid) {
        if (currValue == '#') {
            print("currPos: $currPos")
            for ((endPos, endValue) in grid) {
                if(endValue=='#' && currPos != endPos)
                    print("endpos $endPos")
                    addIfReachable(currPos,endPos,gridb)
            }
            println("")
        }
    }



}

fun addIfReachable(start: Position2D, end: Position2D, gridb: HashMap<Position2D, List<Position2D>>) {
    if (gridb[start].isNullOrEmpty())
        gridb[start] = listOf(end)
    else {
        var endVector = end - start
        for (pos in gridb[start]!!) {
            var possibleBlockVector = pos - start
            if (endVector.divisibleBy(possibleBlockVector)) {
                println("divisible")
            } else {
                gridb.get(start)!! + listOf(end)
                println("adding end:")
                println(gridb[start])
            }

        }
    }
}

private fun printSortedMap(grid: SortedMap<Position2D, Char>) {
    var temp = grid.toSortedMap(compareBy<Position2D> { it.y }.thenBy { it.x })
    var yprev = 0
    for ((key, value) in temp) {
        print("$value")
//
        if (key.y - yprev > 0) {
            println("")
        }
        yprev = key.y
    }
    println("")
}

private fun printHashMap(grid: HashMap<Position2D, Char>) {
    var temp = grid.toSortedMap(compareBy<Position2D> { it.y }.thenBy { it.x })
    var yprev = 0
    for ((key, value) in temp) {
        print("$value")
//        println("$key. and $value")
//
        if (key.y - yprev > 0) {
            println("")
        }
        yprev = key.y
    }
    println("")

//    var temp2 = temp.entries.groupBy { it.key.y }.map { it.value.joinToString ("")  }.joinToString("\n")
//    print(temp2)
}

private fun getInput(filename: String): HashMap<Position2D, Char> {
    var fileReader = BufferedReader(FileReader(filename))
    var x = 0;
    var y = 0;
    var line = fileReader.readLine()
    var grid = HashMap<Position2D, Char>()
    while (line != null) {
        for ((index, value) in line.withIndex()) {
            grid.putIfAbsent(Position2D(index, y), value)
        }
        y++; line = fileReader.readLine()
    }
    return grid
}

