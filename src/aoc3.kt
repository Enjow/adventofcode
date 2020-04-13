import kotlin.math.abs

val FILENAME3A = "day3a.txt"
val FILENAME3B = "day3b.txt"
val grid_size = 20000
//val FILENAME3A = "day3testinput.txt"
//val FILENAME3B = "day3testinputb.txt"
//val grid_size = 1000


fun main(args: Array<String>) {
    val input_a = get_input_str(FILENAME3A)
    val input_b = get_input_str(FILENAME3B)

    var instructions_a_hori = IntArray(input_a.size / 2 + 1)
    var instructions_a_vert = IntArray(input_a.size / 2 + 1)
    var instructions_b_hori = IntArray(input_b.size / 2 + 1)
    var instructions_b_vert = IntArray(input_b.size / 2 + 1)

    for (i in 0 until input_a.size) {
        if (i % 2 == 0) {
            instructions_a_hori[i / 2] = get_direction_hori(input_a[i])
        } else {
            instructions_a_vert[i / 2] = get_direction_vert(input_a[i])
        }
    }

    for (i in 0 until input_b.size) {
        if (i % 2 == 0) {
            instructions_b_hori[i / 2] = get_direction_hori(input_b[i])
        } else {
            instructions_b_vert[i / 2] = get_direction_vert(input_b[i])
        }
    }

/* ------------------------------------------------------- */

    val start_pos = grid_size / 2

    var distanceGridA = Array(grid_size, { IntArray(grid_size) { 0 } })
    var distanceGridB = Array(grid_size, { IntArray(grid_size) { 0 } })
//    distanceGridA[start_pos][start_pos] = 999999
//    distanceGridB[start_pos][start_pos] = 999999

    inscribe_line_dist(distanceGridA, instructions_a_hori, instructions_a_vert, start_pos, input_a.size)
    inscribe_line_dist(distanceGridB, instructions_b_hori, instructions_b_vert, start_pos, input_b.size)

    find_min_distance(distanceGridA, distanceGridB, grid_size, start_pos)
    find_closest_intersection(distanceGridA, distanceGridB, grid_size)
}

fun find_closest_intersection(
    gridA: Array<IntArray>,
    gridB: Array<IntArray>,
    grid_size: Int
) {
    var min = 100000000
    for (x in 0 until grid_size) {
        for (y in 0 until grid_size) {
            if (gridA[y][x] != 0 && gridB[y][x] != 0) {
                var distance = gridA[y][x] + gridB[y][x]
                if (distance < min) {
                    min = distance
                    var distA = gridA[y][x]
                    var distB = gridB[y][x]
//                    println("intersection at x: $x y: $y and distance a: $distA distb: $distB")
                }
            }

        }
    }
    print("Ans B: $min")
}

fun find_min_distance(grida: Array<IntArray>, gridb: Array<IntArray>, grid_size: Int, start_pos: Int) {
    var min = 1000000
    var minx = 10000000
    var miny = 10000000
    for (x in 0 until grid_size) {
        for (y in 0 until grid_size) {
            val value = grida[y][x]

            if (grida[y][x] != 0 && gridb[y][x] != 0) {
                val distance = abs(start_pos - x) + abs(start_pos - y)
                if (distance < min) {
                    min = distance
                    minx = x
                    miny = y
//                    println("$min, miny: $miny, minx: $minx")

                }
            }

        }
    }
    println("Ans: $min, miny: $miny, minx: $minx")

}

fun print_array(grid: Array<IntArray>) {
    for (item in grid) {
        for (point in item) {
            print("$point,")
        }
        println("")
    }
}

fun inscribe_line_dist(
    grid: Array<IntArray>,
    instructions_hori: IntArray,
    instructions_vert: IntArray,
    start_pos: Int,
    input_size: Int
) {
    var x = start_pos
    var y = start_pos
    var dist = 0
    for (i in 0 until input_size) {
        if (i % 2 == 0) {
            val steps = instructions_hori[i / 2]
            set_route_hori_dist(grid, x, y, steps, dist)
            x += steps
            dist += abs(steps)
        } else {
            val steps = instructions_vert[i / 2]
            set_route_vert_dist(grid, x, y, steps, dist)
            y -= steps
            dist += abs(steps)
        }
//        println("dist: $dist")
    }
}


fun get_direction_vert(string_direction: String): Int {
    var result = 0
    when (string_direction[0]) {
        'U' -> result = string_direction.substring(1, string_direction.length).toInt()
        'D' -> result = (string_direction.substring(1, string_direction.length).toInt()) * -1
    }
    return result
}

fun get_direction_hori(string_direction: String): Int {
    var result = 0
    when (string_direction[0]) {
        'R' -> result = string_direction.substring(1, string_direction.length).toInt()
        'L' -> result = (string_direction.substring(1, string_direction.length).toInt()) * -1
    }
    return result
}


fun set_route_hori_dist(grid: Array<IntArray>, x: Int, y: Int, steps: Int, dist: Int) {
    var start = 0
    var end = 0

    if (steps > 0) {
        start = x + 1
        end = x + steps + 1

        var counter = 1
        for (i in start until end) {
            if (grid[y][i] == 0) {
                grid[y][i] = dist + counter
            }
            counter += 1
        }

    } else if (steps < 0) {
        start = x + steps
        end = x

        var counter = 0
        for (i in start until end) {
            if (grid[y][i] == 0) {
                grid[y][i] = dist + counter + abs(steps)
            }
            counter -= 1
        }
    }
}

fun set_route_vert_dist(grid: Array<IntArray>, x: Int, y: Int, steps: Int, dist: Int) {
    var start = 0
    var end = 0

    if (steps > 0) {
        start = y - steps
        end = y
        var counter = 0
        for (i in start until end) {
            if (grid[i][x] == 0) {
                grid[i][x] = dist + counter + steps
            }
            counter -= 1
        }
    } else if (steps < 0) {
        start = y + 1
        end = y - steps + 1
        var counter = 1
        for (i in start until end) {
            if (grid[i][x] == 0) {
                grid[i][x] = dist + counter
            }
            counter += 1
        }
    }
}