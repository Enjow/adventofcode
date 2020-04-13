import java.util.Scanner
import Kattio
import java.io.BufferedReader
import java.io.File
import kotlin.math.floor

fun day1a() {
    var sum = 0
    File("day1.txt").forEachLine {
        val value = it.toFloat()
        sum += (floor(value / 3) - 2).toInt()
    }
    print("solution 1.a: \n")
    println(sum)

}

fun day1b() {
    val list = mutableListOf<Double>()
    File("day1.txt").forEachLine {
        list += it.toDouble()
    }
    var sum: Double = 0.0
    for (element in list) {
        var temp = element
        while (floor(temp / 3) - 2 > 0) {
            temp = floor(temp / 3) - 2
            sum += temp
        }
    }
    println(sum)
}

fun main(args: Array<String>) {
    day1a()
    day1b()
}

