package study.syntax.lines

fun main() {
    execute(
        { println("hello") },
        { println("world") },
        { println("greetings") }
    )
}

inline fun execute(action1: () -> Unit, noinline action2: () -> Unit, crossinline action3: () -> Unit) {
    println("step 1 start")
    action1()
    crossinlineExecute(action2)
    crossinlineExecute { action3() }
    println("step 1 end")
}

fun crossinlineExecute(action:() -> Unit) {
    println("step 2 start")
    action()
    println("step 2 end")
}
