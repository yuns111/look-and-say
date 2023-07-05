package com.yuns.ch1

fun main(args: Array<String>) {
    println(ant(10))
}

private fun ant(num: Int): String {
    var s = "1"
    for (line in 0 until num) {
        s = next(s)
    }
    return s
}

private fun next(s: String): String {
    var length = 1
    var head = s[0]
    var result = ""
    for (i in 1 until s.length) {
        if (s[i] == head) {
            length++
        } else {
            result += length
            result += head
            length = 1
            head = s[i]
        }
    }
    result += length
    result += head
    return result
}