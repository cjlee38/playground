package study.syntax.delegate

import kotlin.properties.Delegates
import kotlin.reflect.KProperty


fun main() {
    val example = Example()
    val p = example.p
    println(p)
}

class Example {
    var p: String by Delegate()
    val p2: String by lazy { "10" }
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}