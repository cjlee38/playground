package study.syntax.nullable

fun String?.isMyNullOrBlank(): Boolean = this == null || this.isBlank()

fun main() {
    val nullable: String? = null
    val nonnull: String = "hello"

    nullable.isMyNullOrBlank()
    nonnull.isMyNullOrBlank()
}