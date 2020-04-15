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



        println("operator $operator")

        //get first and second term
        if (operator == 1 || operator == 2 || operator == 5 || operator == 6 || operator == 7 || operator == 8) {
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
//            println("Parameter $parameters")
//            println("pointer $pointer")
//            println("first term $firstTerm")
//            println("second term $secondTerm")
//            println(operations)
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
                if (firstTerm != 0) {
//                    pointer = operations[operations[pointer + 2]]//Todo Check if ok
//                    pointer = operations[pointer+2]//Todo Check if ok
                    pointer = secondTerm
//                    print("Operator is 5 second term is $secondTerm")

                } else {
                    pointer += 3
//                    println("Operator is 5 pointer became $pointer")

                }
            }
            6 -> {
                if (firstTerm == 0) {
                    pointer = secondTerm

                } else {
                    pointer += 3
                }
            }
            7 -> {
                if (firstTerm < secondTerm) {
                    operations[operations[pointer + 3]] = 1
                } else {
                    operations[operations[pointer + 3]] = 0
                }

            }
            8 -> {
                if (firstTerm == secondTerm) {
                    operations[operations[pointer + 3]] = 1
                } else {
                    operations[operations[pointer + 3]] = 0
                }
            }
            9 -> println("successfully ended program")
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