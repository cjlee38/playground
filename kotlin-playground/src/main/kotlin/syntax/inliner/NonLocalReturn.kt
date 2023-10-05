package syntax.inliner


inline fun <T> List<T>.method(lambda: (T) -> Unit) {
    for (index in indices) {
        lambda(this[index])
    }
    println("done")
}

fun main() {
    val a = listOf(1, 2)
    a.method {
        if (it == 1) {
            return
        }
        println("$it is number")
    }
}
