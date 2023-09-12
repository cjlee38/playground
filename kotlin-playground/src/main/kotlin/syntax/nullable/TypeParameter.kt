package study.syntax.nullable

fun <T> printHashCode(t: T) {
    // t에서 null을 받을 수 있다.
    // 만약 <T> 가 아닌 <T: Any> 를 사용한다면 null을 받을 수 없다.
    println(t?.hashCode())
    println(t.hashCode())
    // 위 두개의 경우 사용되는 함수가 다르다.
}

fun main() {
    printHashCode(null)
}