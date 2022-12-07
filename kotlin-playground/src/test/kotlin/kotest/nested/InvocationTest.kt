package kotest.nested

import io.kotest.assertions.asClue
import io.kotest.core.spec.style.StringSpec
import io.kotest.mpp.newInstanceNoArgConstructor
import kotlin.jvm.internal.CallableReference
import kotlin.reflect.KClass
import kotlin.reflect.KDeclarationContainer
import kotlin.reflect.KFunction1


open class Greetings {
    fun hello() {
        println("hello")
    }

    class InnerGreetings {

    }
}

class WrongGreetings : Greetings() {
    fun hi() {
        println("hi")
    }
}

class InvocationTest : StringSpec({

    "올바른 인스턴스로 메소드를 호출한다" {
        val klass = Greetings()::class
        val kFunction: KFunction1<Greetings, Unit> = Greetings::hello
        if (kFunction is CallableReference) {
            val owner: KDeclarationContainer = kFunction.owner
        }
        val instance = klass.newInstanceNoArgConstructor()
        kFunction.invoke(instance)
    }

    "메소드로부터로 메소드를 호출한다" {
        val kFunction = Greetings::hello
        if (kFunction is CallableReference) {
            val kclass = kFunction.owner as KClass<*>
            println("kclass = ${kclass}")
            kFunction.invoke(kclass.newInstanceNoArgConstructor() as Greetings)
        }
    }
})
