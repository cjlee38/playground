package syntax.nullable

fun main() {
    val str: String? = null
    val s = (null as? String?)?.uppercase() // 이렇게도 가능하다.

//    val s2 = str?.uppercase() // 축약하면 이렇게 쓸 수 있다.

    val s3 = if (str?.length == 3) str.lowercase() else 0 // null 이 아닌 비교도 가능하다.
}
