package study.syntax.wrappertype

import java.util.HashSet

interface KtGenerator<T> {
    fun generate(): T
}

class IntKtGenerator: KtGenerator<Int> {
    override fun generate(): Int {
        return 42
    }
}


class UnitKtGenerator : KtGenerator<Unit> {
    override fun generate() {
        println("unitGenerator called, and I wanna return nothing !")
    }

}