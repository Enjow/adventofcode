import java.io.BufferedReader
import java.io.FileReader
import kotlin.math.truncate

private const val FILENAME8 = "day8.txt"
private const val WIDTH = 25
private const val HEIGHT = 6
private const val PICSIZE = WIDTH * HEIGHT


fun main(args: Array<String>) {
    // Naive 8a
    var pictures = getInput(FILENAME8)
    var finalPicture = HashMap<Position2D, Char>()
    var layer = 1
    for (picture in pictures) {
        for ((key, value) in picture) {
            if (value == '1' && !finalPicture.containsKey(key)) {
                finalPicture.putIfAbsent(key, '@')
            } else if (value == '0' && !finalPicture.containsKey(key)) {
                finalPicture.putIfAbsent(key, ' ')
            } else if (value == '2' && !finalPicture.containsKey(key) && picture == pictures.last()) {
                finalPicture.putIfAbsent(key, '-')
            }

        }
        layer++
    }
    println("Result")
    printPicture(finalPicture)
}

private fun printPicture(picture: HashMap<Position2D, Char>) {
    var x = 0
    var y = 0
    for (index in 0 until PICSIZE) {
        if (index % WIDTH == 0)
            println("")
        x = index % (WIDTH); y = truncate(index % PICSIZE / WIDTH.toDouble()).toInt();
        print(picture[Position2D(x, y)])
    }
    println("")
}


private fun getInput(filename: String): MutableList<HashMap<Position2D, Char>> {
    var fileReader = BufferedReader(FileReader(filename))
    val allPixels = fileReader.readLine()
    var x = 0;
    var y = 0;
    var pictures = mutableListOf<HashMap<Position2D, Char>>()

    var picture = HashMap<Position2D, Char>()
    for ((index, pixel) in allPixels.withIndex()) {

        x = index % (WIDTH); y = truncate(index % PICSIZE / WIDTH.toDouble()).toInt();

        picture[Position2D(x, y)] = pixel
        if ((index + 1) % PICSIZE == 0 && index+1 >= PICSIZE) {
            pictures.add(picture)
            picture = HashMap(picture) // Not very nice
        }
    }
    return pictures
}
