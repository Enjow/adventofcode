import java.io.BufferedReader
import java.io.FileReader

private const val FILENAME6 = "day6b.txt"

fun main(args: Array<String>) {
    var graph = getStringInput(FILENAME6)
    var graphCounter = HashMap<String, Int>()
    for ((child, parent) in graph) {
        if (child == "YOU" || child == "SAN") {
            println("child in main $child")
            markPathWay(graph[child]!!, graph, graphCounter)
        }
    }
    var sum = 0  
    for ((key, value) in graphCounter) {
        println("$key has value $value")
        if(value==1)
            sum += value
    }
    print("Answer: $sum")
}

private fun markPathWay(node: String, graph: HashMap<String, String>, graphCounter: HashMap<String, Int>) {
    if (graph[node] == "COM") {
        graphCounter.merge(node, 1,Int::plus)
        println("first node found $node ")
    } else {
        var parent = graph[node]

        println("parent in loop $parent")
        println("node in loop $node")
        graphCounter.merge(node, 1,Int::plus)
        markPathWay(graph[node]!!, graph, graphCounter)
    }
}

private fun getStringInput(filename: String): HashMap<String, String> {
    var fileReader = BufferedReader(FileReader(filename))
    val inputList = mutableListOf<List<String>>()

    var line = fileReader.readLine()
    var graph = HashMap<String, String>()
    while (line != null) {
        var splitLine = line.split(")")
        var parent = splitLine[0];
        var child = splitLine[1]
        if (graph.containsKey(child))
            error("Child node twice in input")
        graph.putIfAbsent(child, parent)
        line = fileReader.readLine()
//        println("$child")
    }
    return graph
}