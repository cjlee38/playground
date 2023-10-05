package syntax.wrappertype

fun main() {
    var value: Int = 2147483647
    value += 1
    assert(value == -2147483648)
}
