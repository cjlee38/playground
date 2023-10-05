package syntax.generics

open class Parent(val id: Long)

class ChildOne(id: Long): Parent(id)

class ChildTwo(id: Long): Parent(id)

class MyList<in E: Parent> {

    var element: @UnsafeVariance E? = null

    fun add(element: E) {
        this.element = element
    }
}

fun main() {
    val children = listOf(ChildOne(1), ChildOne(2))
    // List는 읽기전용이므로 <out T>로 선언되어있다.
    // 따라서 List<Parent> 자리에 List<ChildOne>을 넘겨줄 수 있다.
    printInstance(children)
    addMy(MyList<Parent>())
}

fun printInstance(elements: List<Parent>) {
    elements.forEach { println(it) }
}

fun addMy(myList: MyList<ChildOne>) {
    // pass
}
