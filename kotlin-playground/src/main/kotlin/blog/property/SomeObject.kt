package blog.property

class SomeObject {
    var someInteger: Int = 123
    val someString: String = "string"
}

class Age {
    var value = 20
        get() = field + 1
        set(value) {
            field = value - 2
        }
    val isAdult: Boolean
        get() = value > 20

    override fun toString(): String {
        return "Age(value=$value, isAdult=$isAdult)"
    }


}

class Person {

    lateinit var age: Age
    fun print() {
        println(age)
    }
}

fun main() {
    val person = Person()
    person.age = Age()
    person.print()
}
