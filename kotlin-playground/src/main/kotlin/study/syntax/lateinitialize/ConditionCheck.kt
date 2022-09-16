package study.syntax.lateinitialize

class ConditionCheck {
    lateinit var nonPrimitive: Class
//    lateinit var primitive: Int // 불가능

    fun test() {
        nonPrimitive.method()
    }
}

class Class {
    fun method() = println("hello class")
}

