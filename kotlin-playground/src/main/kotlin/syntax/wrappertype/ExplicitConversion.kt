package study.syntax.wrappertype

fun main() {
    val a = 42;
    val b = 42L;

//    println(a == b) // 컴파일 에러가 발생한다
    val l = b + a // 오버로딩 되어있다.
}