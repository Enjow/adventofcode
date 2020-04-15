import java.io.BufferedReader
import java.io.FileReader
import kotlin.math.truncate

private val FILENAME5 = "day5.txt"

fun main(args: Array<String>) {
    val input = getSingleLineInput(FILENAME5)
    val operations = input.toMutableList()
//    val start = 1;
    val start = 5;
    var pointer = 0;

    while (pointer < operations.size) {
        var instruction = operations[pointer]
        var currentInput = start
        var operator = instruction % 10
        var parameters = truncate(instruction.toDouble() / 100)
//        println("Parameters $parameters")
        var firstTerm: Int = 0
        var secondTerm: Int = 0


        if (operator == 9)
            println("successfully ended program")

        println("operator $operator")

        //get first and second term
        if (operator == 1 || operator == 2) {
            firstTerm = if (parameters % 10 == 1.0) {
                operations[pointer + 1]//immidiate mode
            } else {
                operations[operations[pointer + 1]]//position mode
            }
            secondTerm = if (truncate(parameters / 10) % 10 == 1.0) {
                operations[pointer + 2] //immidiate mode
            } else {
                operations[operations[pointer + 2]] // position mode
            }
//            print("secondTerm Parameter ")
//            println(truncate(parameters / 10) % 10)
//            println("first term $firstTerm")
//            println("second term $secondTerm")
        }

        //execute

        when (operator) {
            1 -> {
                operations[operations[pointer + 3]] = firstTerm + secondTerm
            }
            2 -> {
                operations[operations[pointer + 3]] = firstTerm * secondTerm
            }
            3 -> {
                operations[operations[pointer + 1]] = currentInput
            }
            4 -> {
                currentInput = operations[operations[pointer + 1]]
                println(currentInput)
            }
            5 -> {
                if (operations[pointer + 1] != 0) {
                    pointer = operations[pointer + 2]
                } else {
                    pointer += 2
                }
            }
            6 -> {
                if (operations[pointer + 1] == 0) {
                    pointer = operations[pointer + 2]
                } else {
                    pointer += 2
                }
            }
            7 -> {
                if (operations[pointer + 1] < operations[pointer + 2]) {
                    operations[operations[pointer + 3]] = 1
                } else {
                    operations[operations[pointer + 3]] = 0
                }

            }
            8 -> {
                if (operations[pointer + 1] == operations[pointer + 2]) {
                    operations[operations[pointer + 3]] = 1
                } else {
                    operations[operations[pointer + 3]] = 0
                }
            }
            else -> error("nope")
        }

        if (operator != 5 && operator != 6)
            pointer += pointer_shift(operator)

    }
}

private fun List<String>.operationTwo(operation: Char, first: Int, second: Int, immidiate: Boolean = true) {

}


fun pointer_shift(operator: Int): Int {
    when (operator) {
        1 -> return 4
        2 -> return 4
        3 -> return 2
        4 -> return 2
        7 -> return 4
        8 -> return 4
        else -> println("Warning operator is $operator")
    }
    return -1
}


private fun getSingleLineInput(filename: String): List<Int> {
    val fileReader = BufferedReader(FileReader(filename))
    var inputList = fileReader.readLine().split(",").map { it.toInt() }
    return inputList
}