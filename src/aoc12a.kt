import java.io.BufferedReader
import java.io.FileReader

private const val FILENAME12 = "day12.txt"

private const val ITERATION = 1000
fun main(args: Array<String>) {
//    partA()
    partB()
}

fun partB() {
    var planets = getInput(FILENAME12)
    var speeds = mutableListOf<Position3D>()

    for (i in 0 until planets.size) {
        speeds.add(Position3D(0, 0, 0))
    }

    for (iteration in 0 until ITERATION) {
        speeds.updateSpeedVectors(planets)
        planets.updatePositions(speeds)
        planets.map { print("${it.x}, ") }
        println("")
        //        print(planets)
        //        println(speeds)
    }

}

private fun partA() {
    var planets = getInput(FILENAME12)
    var speeds = mutableListOf<Position3D>()

    for (i in 0 until planets.size) {
        speeds.add(Position3D(0, 0, 0))
    }

    for (iteration in 0 until ITERATION) {
        speeds.updateSpeedVectors(planets)
        planets.updatePositions(speeds)
//        print(planets)
//        println(speeds)
    }

    print("Answer: ")
    println(getEnergy(planets, speeds))
}

private fun MutableList<Position3D>.updatePositions(speeds: MutableList<Position3D>) {
    for ((i, planet) in this.withIndex()) { //Update positions
        this[i] = planet + speeds[i]
    }
}

private fun MutableList<Position3D>.updateSpeedVectors(planets: MutableList<Position3D>) {
    for ((a, planetA) in planets.withIndex()) { //compare all planets to each other.
        for ((b, planetB) in planets.withIndex()) {
            this[a].getDirection(planetA, planetB)
        }
    }
}

private fun getEnergy(planets: MutableList<Position3D>, speeds: MutableList<Position3D>): Int {
    var sum = 0
    for (i in 0 until planets.size) {
        sum += planets[i].energy() * speeds[i].energy()
    }
    return sum
}

private fun Position3D.getDirection(planetA: Position3D, planetB: Position3D) {
    if (planetA.x < planetB.x) this.x++
    else if (planetA.x > planetB.x) this.x--

    if (planetA.y < planetB.y) this.y++
    else if (planetA.y > planetB.y) this.y--

    if (planetA.z < planetB.z) this.z++
    else if (planetA.z > planetB.z) this.z--
}


private fun getInput(filename: String): MutableList<Position3D> {
    var fileReader = BufferedReader(FileReader(filename))
    var line = fileReader.readLine()
    var planets = mutableListOf<Position3D>()

    while (line != null) {
        var x = line.substringAfter('x').substringAfter('=').substringBefore(',').toInt()
        var y = line.substringAfter('y').substringAfter('=').substringBefore(',').toInt()
        var z = line.substringAfter('z').substringAfter('=').substringBefore('>').toInt()
        planets.add(Position3D(x, y, z))
        line = fileReader.readLine()
    }
    return planets
}