import java.io.BufferedReader
import java.io.FileReader
import java.lang.Math.abs

private const val FILENAME3A = "day3_improved.txt"
private const val FILENAME3B = "day3_improved.txt"
//val FILENAME3A = "day3testinput.txt"
//val FILENAME3B = "day3testinputb.txt"
//val grid_size = 1000

fun List<instruction>.toGrid(): HashMap<Pos, Int> {
    var grid: HashMap<Pos, Int> = HashMap<Pos, Int>()
    var position = Pos(0, 0)
    var distance = 0
    for (instr in this) {
        repeat(instr.length) {
            position += instr.dir
            distance++
            grid.putIfAbsent(position, distance)
        }
    }
    return grid
}

fun main(args: Array<String>) {
    val instructions =
        getInputStr(FILENAME3A).map { line ->
            line.map { instructionString ->
                instruction(
                    Dir.valueOf(instructionString[0].toString()),
                    instructionString.substring(1).toInt()
                )
            }
        }


    val instructionsA = instructions[0]
    val instructionsB = instructions[1]

    val gridA = instructionsA.toGrid()
    val gridB = instructionsB.toGrid()
    print("Answer A: ")
    println(gridA.keys.intersect(gridB.keys).map { it.manhattan_dist }.min()!!)
    print("Answer B: ")
    println(gridA.keys.intersect(gridB.keys).map { gridA[it]!! + (gridB[it]!!) }.min()!!)
}

data class instruction(val dir: Dir, val length: Int)

data class Pos(var x: Int, var y: Int) {
    operator fun plus(pos: Pos): Pos {
        return Pos(this.x + pos.x, this.y + pos.y)
    }

    operator fun plus(dir: Dir): Pos {
        return (this + dir.pos)
    }

    val manhattan_dist = abs(x) + abs(y)
}

enum class Dir(val pos: Pos) {
    U(Pos(0, 1)),
    D(Pos(0, -1)),
    R(Pos(1, 0)),
    L(Pos(-1, 0))
}


private fun getInputStr(filename: String): MutableList<List<String>> {
    var fileReader = BufferedReader(FileReader(filename))
    val inputList = mutableListOf<List<String>>()

    var line = fileReader.readLine()
    while (line != null) {
        inputList.add(line.split(","))
        line = fileReader.readLine()
    }
    return inputList
}