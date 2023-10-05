package syntax.receiver

fun String.lastChar(): Char {
    return get(this.length - 1)
}

fun main() {
    val str = "hello world"
    val extensionVersion = str.lastChar()
    val receiverVersion = with(str) {
        get(this.length - 1)
    }

    println(extensionVersion)
    println(receiverVersion)
}
