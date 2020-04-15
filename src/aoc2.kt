import java.io.BufferedReader
import java.io.FileReader
import java.lang.Math.floor

val FILENAME2 = "day2.txt"

fun day2a() {
    var input = get_input_int(FILENAME2)
    input[1] = 12
    input[2] = 2
    loop@ for (pos in 0 until input.size step 4) {
        var operator = input[pos]
        if (operator == 99) {
            println("breaking at position $pos")
            break@loop
        }

        var firstIndex = input[pos + 1]
        var secondIndex = input[pos + 2]
        var storingIndex = input[pos + 3]

//        println("pos: $pos, op: $operator, first $firstIndex, sec: $secondIndex, storingIndex: $storingIndex")
        when (operator) {
            1 -> input[storingIndex] = input[firstIndex] + input[secondIndex]
            2 -> input[storingIndex] = (input[firstIndex] * input[secondIndex])
            99 -> error("wrong break")
            else -> error("not correct operator")
        }
    }
    print("Answer at position 0: " + input[0])
}

private fun machine(noun: Int, verb: Int): Int {
    var input = get_input_int(FILENAME2)
    input[1] = noun
    input[2] = verb
    loop@ for (pos in 0 until input.size step 4) {
        var operator = input[pos]
        if (operator == 99) {
            println("breaking at position $pos")
            break@loop
        }

        var firstIndex = input[pos + 1]
        var secondIndex = input[pos + 2]
        var storingIndex = input[pos + 3]

//        println("pos: $pos, op: $operator, first $firstIndex, sec: $secondIndex, storingIndex: $storingIndex")
        when (operator) {
            1 -> input[storingIndex] = input[firstIndex] + input[secondIndex]
            2 -> input[storingIndex] = (input[firstIndex] * input[secondIndex])
            99 -> error("wrong break")
            else -> error("not correct operator")
        }
    }
    println("Answer at position 0: " + input[0])
    return input[0]
}

fun day2b(): List<Int> {
    machine(0, 0)
    machine(0, 1)
    machine(1, 0)
    machine(1, 1)
    machine(2, 1)
    // noun increases with 2680666-1870666=810000
    // verb increases with 1
    //  0,0 gives 11590668
    var noun_diff = machine(1, 0) - machine(0, 0)
    var verb_diff = machine(0, 0) - machine(1, 0)
    val output = listOf(verb_diff, noun_diff)
    return output
}


fun main(args: Array<String>) {
    day2a()

    // 2b
    val verbNounList = day2b()
    var target = 19690720
    var diff = target - machine(0, 0)
    println(diff)
    val noun = floor((diff / verbNounList[1]).toDouble())
    val verb = floor((diff % verbNounList[0]).toDouble())
    val ans = (noun * 100.0) + verb
    println("Answer 2b: $ans ")
}


private fun get_input_int(filename: String): MutableList<Int> {
    var fileReader = BufferedReader(FileReader(filename))
    val inputList = mutableListOf<Int>()

    var line = fileReader.readLine()
    while (line != null) {
        line.split(",").map { inputList.add(it.toInt()) }
        line = fileReader.readLine()
    }
    return inputList
}