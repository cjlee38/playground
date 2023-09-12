package study.syntax.receiver

fun main() {
    var age = 5
    val hello = {
        println("age = ${age}")
        age++
    }
    println(age)  // 5
    hello()  // 변경 가능한 변수 포획_ 함수 실행 시 접근 가능
    println(age)  // 6
    age = 10
    hello()
}