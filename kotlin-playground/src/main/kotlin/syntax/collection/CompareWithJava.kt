package syntax.collection

import java.util.HashSet as JavaHashSet
import kotlin.collections.HashSet as KtHashSet

fun main() {
    val javaSet = JavaHashSet<Int>()
    val ktSet = KtHashSet<Int>()
    val ktSet2 = hashSetOf<Int>()
    val ktSet3 = setOf<Int>(1, 2, 3)

    javaSet.add(1)
    ktSet.add(1)
    ktSet2.add(1)

    println("javaSet class = ${javaSet::class}")
    println("ktSet class = ${ktSet::class}")
    println("ktSet2 class = ${ktSet2::class}")
    println("ktSet3 class = ${ktSet3::class}")

    println("compare Kt1 to Kt2 = ${ktSet == ktSet2}")
    println("compare Kt1 to Java = ${javaSet == ktSet}")
}
