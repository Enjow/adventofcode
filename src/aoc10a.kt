import java.io.BufferedReader
import java.io.FileReader
import java.lang.Integer.max
import java.lang.Math.abs
import java.lang.Math.acos
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.round
import kotlin.math.truncate

private const val FILENAME10 = "day10.txt"


fun main(args: Array<String>) {
    var gridHash = getInput(FILENAME10)
    var grid = gridHash.toSortedMap(compareBy<Position2D> { it.y }.thenBy { it.x })
    var gridDirections = HashMap<Position2D, MutableSet<Position2D>>()
    var gridAngles = mutableMapOf<Position2D, Double>()
    var gridNorms = mutableMapOf<Position2D, Double>()
    var gridTerminationOrder = mutableMapOf<Position2D, Int>()
    for ((currPos, currValue) in grid) {
        if (currValue == '#') {
            addReachableDirections(currPos, grid, gridDirections)
        }
    }
    //Print max value
    var maxVal = gridDirections.map { it.value.count() }.max()
    var maxKey = Position2D(1, 1)
    for ((key, value) in gridDirections) {
        if (value.count() == maxVal) {
            maxKey = key
            println("$key has ${value.count()} asteroids in its direction")
        }
    }


    // B
    var directions = gridDirections[maxKey]
    var angles = getAngles(directions!!)
    addReachableAngles(maxKey, grid, gridAngles)
    addReachableNorms(maxKey, grid, gridNorms)
    var counter = 1
    while (gridAngles.size > 0)
        for ((i, angle) in angles.withIndex()) { //One Rotation
            var positionsInAngle = gridAngles.filterValues { abs(it - angle) < 0.1 }.keys
            if (positionsInAngle.size==0){
                continue
            }
            var positionInAngle = gridNorms.filterKeys { it in positionsInAngle }.minBy { it.value }!!.key

            gridTerminationOrder[positionInAngle] = counter
            gridNorms.remove(positionInAngle)
            gridAngles.remove(positionInAngle)
            counter++
        }

    var twoHundred=gridTerminationOrder.filterValues { it==200 }
    var ans = twoHundred.keys.map{ it.x*100+it.y }
    println("ANSWER B: $ans")
    print(gridTerminationOrder.filterValues { it==200 })
//    printIntGrid(gridTerminationOrder)
}

fun getAngle(direction: Position2D): Double {
    var res = mutableListOf<Double>()
    var angle = (acos(-direction.y / direction.norm()) * 180 / Math.PI) //OBS flipped negative y direction
    if (direction.x < 0) {
        angle = -angle+360.0 // Todo Check this
    }
    return Math.round(angle * 1000.0) / 1000.0
}

fun addReachableDirections(
    activePos: Position2D,
    grid: SortedMap<Position2D, Char>,
    gridDirections: HashMap<Position2D, MutableSet<Position2D>>
) {
    gridDirections.putIfAbsent(activePos, mutableSetOf())

    for ((passivePos, passiveValue) in grid) {
        if (passiveValue == '#' && passivePos != activePos) {
            var direction = getSmallestVector(passivePos - activePos)

            gridDirections[activePos]!!.add(direction)
        }
    }
}

fun addReachableAngles(
    activePos: Position2D,
    grid: SortedMap<Position2D, Char>,
    gridAngles: MutableMap<Position2D, Double>
) {
    for ((passivePos, passiveValue) in grid) {
        if (passiveValue == '#' && passivePos != activePos) {
            var angle = getAngle(passivePos - activePos)
            gridAngles.putIfAbsent(passivePos, angle)
        }
    }
}

fun addReachableNorms(
    activePos: Position2D,
    grid: SortedMap<Position2D, Char>,
    gridNorms: MutableMap<Position2D, Double>
) {
    for ((passivePos, passiveValue) in grid) {
        if (passiveValue == '#' && passivePos != activePos) {
            gridNorms.putIfAbsent(passivePos, (passivePos - activePos).norm())
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

private fun getInput(filename: String): HashMap<Position2D, Char> {
    var fileReader = BufferedReader(FileReader(filename))
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


fun getAngles(directions: MutableSet<Position2D>): MutableList<Double> {
    var res = mutableListOf<Double>()
    for ((i, direction) in directions.withIndex()) {
        res.add(getAngle(direction))
    }
    res.sort()
    return res
}