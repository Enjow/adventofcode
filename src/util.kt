import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

data class Position2D(var x: Int, var y: Int) {
    operator fun minus(pos: Position2D): Position2D = Position2D(this.x - pos.x, this.y - pos.y)
    fun norm() = sqrt(this.x.toDouble().pow(2) + this.y.toDouble().pow(2))
    fun divisibleBy(pos: Position2D): Boolean {
        if ((this.x != 0 && pos.x == 0) ||
            (this.y != 0 && pos.y == 0)
        ) {
            return false
        } else if (
            (this.x == 0 && pos.x == 0) ||
            (this.y == 0 && pos.y == 0) ||
            this.x % pos.x == 0 && this.y % pos.y == 0 && this.x / pos.x == this.y / pos.y
        ) {
            return true
        } else {
            Error("Position2D divisibleBy function unknown case")
        }
        return false
    }
}


data class Position3D(var x: Int, var y: Int, var z: Int) {
    operator fun minus(pos: Position3D): Position3D = Position3D(this.x - pos.x, this.y - pos.y, this.z - pos.z)
    operator fun plus(pos: Position3D): Position3D = Position3D(this.x + pos.x, this.y + pos.y, this.z + pos.z)
    fun norm() = sqrt(this.x.toDouble().pow(2) + this.y.toDouble().pow(2) + this.z.toDouble().pow(2))
    fun energy() = abs(this.x)+abs(this.y)+abs(this.z)
}



fun printIntGrid(grid: MutableMap<Position2D, Int>) {
    var temp = grid.toSortedMap(compareBy<Position2D> { it.y }.thenBy { it.x })
    var yprev = 0
    for ((key, value) in temp) {
        print("$value, ")
//
        if (key.y - yprev > 0) {
            println("")
        }
        yprev = key.y
    }
    println("")
}