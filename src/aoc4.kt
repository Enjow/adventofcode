fun main(args: Array<String>) {
    val start = 109165
    val end = 576723
//    val start = 1134489
//    val end = start

    var counter = 0
    // six digit number. always true
    // withing range always true

    //4a
    for (number in start..end) {
        if (adjascent_digits(number) && increasing_digits(number)) {
//            println(number)
            counter += 1
        }
    }

    println("Answer 4a: $counter")

    // 4b
    counter = 0
    for (number in start..end) {
        if (adjascent_digits_exact(number) && increasing_digits(number)) {
            println(number)
            counter += 1
        }
    }
    println("Answer 4b: $counter")

}

fun increasing_digits(number: Int): Boolean {
    val number_string = number.toString()
    var previous_letter = number_string[0]

    for (char in number_string) { //compares first char with itself
        if (char < previous_letter) {
//            println("not increasing $number, $char, $previous_letter")
            return false
        }
//                    println("increasing $number, $char, $previous_letter")

        previous_letter = char
    }
    return true
}

fun adjascent_digits(number: Int, adj_streak: Int = 2): Boolean {
    var max_adj = 0
    var counter = 1
    val number_string = number.toString()
    var previous_letter = number_string[0]
    for (char in number_string.substring(1)) {
        if (char == previous_letter) {
            counter += 1
        } else {
            counter = 1
        }
        if (counter > max_adj) {
            max_adj = counter
        }

        previous_letter = char
    }
    if (max_adj >= adj_streak) {
        return true
    } else {
        return false
    }
}


fun adjascent_digits_exact(number: Int, adj_streak: Int = 2): Boolean {
    var counter = 1
    val number_string = number.toString()
    var previous_letter = number_string[0]
    for (char in number_string.substring(1)) {
        if (char == previous_letter) {
            counter += 1
        } else {
            if (counter == 2) {
                return true
            }
            counter = 1
        }
        previous_letter = char
    }
    if (counter == 2) {//two last digits are same
        return true
    }
    return false
}