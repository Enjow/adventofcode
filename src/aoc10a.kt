import java.io.BufferedReader
import java.io.FileReader
import java.lang.Integer.max
import java.lang.Math.abs
import java.util.*
import kotlin.collections.HashMap

private const val FILENAME10 = "day10.txt"


fun main(args: Array<String>) {
    var gridHash = getInput(FILENAME10)
    var grid = gridHash.toSortedMap(compareBy<Position2D> { it.y }.thenBy { it.x })

    var gridb = HashMap<Position2D, MutableSet<Position2D>>()

    for ((currPos, currValue) in grid) {
        if (currValue == '#') {
            addReachableDirections(currPos, grid, gridb)
        }
    }

    var maxVal = gridb.map { it.value.count() }.max()
    for ((key, value) in gridb) {
        if (value.count() == maxVal) {
            println("Position $key has value: ${value.count()}")
        }
    }

    
}


fun addReachableDirections(
    activePos: Position2D,
    grid: SortedMap<Position2D, Char>,
    gridb: HashMap<Position2D, MutableSet<Position2D>>
) {
    if (gridb[activePos].isNullOrEmpty()) {
        gridb.putIfAbsent(activePos, mutableSetOf())
    }
    for ((passivePos, passiveValue) in grid) {
        if (passiveValue == '#' && passivePos != activePos) {
            var direction = getSmallestVector(passivePos - activePos)

            gridb[activePos]!!.add(direction)
//            println("adding direction: $direction from activePos: $activePos" +
//                    "and passivePos: $passivePos")
        }
    }
}


fun getSmallestVector(pos: Position2D): Position2D {
    for (denom in (max(abs(pos.x), abs(pos.y)) downTo 1)) {
        if (pos.x % denom == 0 && pos.y % denom == 0) {
            return Position2D(pos.x / denom, pos.y / denom)
        }
    }
    return pos
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

