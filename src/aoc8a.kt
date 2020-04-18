import java.io.BufferedReader
import java.io.FileReader
import java.lang.Integer.min

private const val FILENAME8 = "day8.txt"

fun main(args: Array<String>) {
    // Naive 8a
    var fileReader = BufferedReader(FileReader(FILENAME8))
    val allPixels = fileReader.readLine()
    var numberOfOnes = 0
    var numberOfTwos = 0
    var numberOfZeros = 0
    var numberOfOther = 0
    var minNumberOfZeros = 9999999
    var checkAmountNumberOfOnes = 0
    var checkAmountNumberOfTwos = 0
    var checkAmountNumberOfOther = 0
    var counter = 1
    var resultLayer = 0

    for (pixel in allPixels) {
        when (pixel) {
            '0' -> numberOfZeros++
            '1' -> numberOfOnes++
            '2' -> numberOfTwos++
            else -> numberOfOther++
        }

        if (counter >= (25 * 6) && counter % (25 * 6) == 0) {
            if (minNumberOfZeros > numberOfZeros) {
                minNumberOfZeros = numberOfZeros
                checkAmountNumberOfOnes = numberOfOnes
                checkAmountNumberOfTwos = numberOfTwos
                checkAmountNumberOfOther = numberOfOther
            }
            resultLayer = counter / (25 * 6); numberOfOther = 0; numberOfTwos = 0; numberOfOnes = 0; numberOfZeros = 0;
//            println("Result layer is $resultLayer, number of zeros: $minNumberOfZeros, number of ones: $checkAmountNumberOfOnes")
//            println("Counter: $counter")
        }
        counter++
    }
    println("Result layer is $resultLayer, number of zeros: $minNumberOfZeros, number of ones: $checkAmountNumberOfOnes, number of twos: $checkAmountNumberOfTwos, other numbers: $checkAmountNumberOfOther")
    println("Counter: $counter")

}


private fun getStringInput(filename: String): HashMap<String, String> {
    var fileReader = BufferedReader(FileReader(filename))
    val inputList = mutableListOf<List<String>>()

    var line = fileReader.readLine()
    var graph = HashMap<String, String>()
    while (line != null) {

    }
    return graph
}