package com.yuns

import java.util.function.Function

fun main(args: Array<String>) {
    println(ant(10))
}

private fun ant(num: Int): List<Int>? {
    var s = listOf(1)
    for (i in 0 until num) {
        s = next(s)
    }
    return s
}

private fun next(ns: List<Int>): List<Int> {
    return concat(map({ g -> listOf(g.size, g[0]) }, group(ns)))
}


private fun <A> group(aList: List<A>): List<List<A>> {
    val ass: MutableList<List<A>> = ArrayList()
    var g: MutableList<A>? = null
    for (a in aList) {
        if (g == null || g[0] != a) {
            g = ArrayList()
            ass.add(g)
        }
        g.add(a)
    }
    return ass
}

private fun <A, B> map(func: Function<A, B>, aList: List<A>): List<B> {
    val bs: MutableList<B> = ArrayList()
    for (a in aList) {
        bs.add(func.apply(a))
    }
    return bs
}

private fun <A> concat(ass: List<List<A>>): List<A> {
    val list: MutableList<A> = ArrayList()
    for (a in ass) {
        list.addAll(a)
    }
    return list
}