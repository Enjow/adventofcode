import java.io.BufferedReader
import java.io.FileReader

private const val FILENAME6 = "day6.txt"

fun main(args: Array<String>) {
    var graph = getStringInput(FILENAME6)
    var graphCounter = HashMap<String, Int>()
    for ((child, parent) in graph) {
        graphCounter.putIfAbsent(child, distanceOfNode(child, graph))
    }
    var sum = 0
    for ((key, value) in graphCounter) {
        println("$key has value $value")
        sum += value
    }
    print(sum)
}

fun distanceOfNode(node: String, graph: HashMap<String, String>): Int {
    if (graph[node] == "COM") {
        println("first node found $node ")
        return 1
    } else {
        return distanceOfNode(graph[node]!!, graph)+1
    }
}


private fun getStringInput(filename: String): HashMap<String, String> {
    var fileReader = BufferedReader(FileReader(filename))
    val inputList = mutableListOf<List<String>>()

    var line = fileReader.readLine()
    var graph = HashMap<String, String>()
    while (line != null) {
        var splitLine = line.split(")")
        var parent = splitLine[0]
        var child = splitLine[1]
//        graph.merge(child , mutableListOf(parent)) { a, b -> a.addAll(b); a } // Ask bjorn about the details or google when not tired
        if (graph.containsKey(child))
            error("Child node twice in input")
        graph.putIfAbsent(child, parent)
//        println(graph[child])
        line = fileReader.readLine()
    }
    return graph
}