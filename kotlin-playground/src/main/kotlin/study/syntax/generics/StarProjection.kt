package study.syntax.generics

fun main() {
    val strings = listOf("hello", "world")
    printStar(strings)
}

fun printStar(list: List<*>) {
    val get = list.get(0) // any

    list.forEach { println(it) }
}

class Bucket<T>