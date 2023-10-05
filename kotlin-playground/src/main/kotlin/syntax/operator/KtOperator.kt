package syntax.operator

data class KtOperator(val v: Int) {

    operator fun plus(other: KtOperator): KtOperator {
        return KtOperator(this.v + other.v)
    }
}

operator fun JavaOperator.plus(other: JavaOperator): JavaOperator {
    return this.otherNamePlus(other)
}

fun main() {
    val kLeft = KtOperator(3)
    val kRight = KtOperator(5)

    val kSum = kLeft + kRight
    println(kSum)

    val jLeft = JavaOperator(5)
    val jRight = JavaOperator(3)

    val jSum = jLeft + jRight
    println(jSum)
}
