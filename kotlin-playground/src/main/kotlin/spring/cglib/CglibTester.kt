package spring.cglib

import org.springframework.cglib.proxy.Enhancer
import org.springframework.cglib.proxy.MethodInterceptor

open class CglibTester(val value: Int = 100)

fun main() {
    val proxy = generatorBytecodeByCglib(CglibTester())
    println("proxy = ${proxy}")
}

fun <T : Any> generatorBytecodeByCglib(target: T): ByteArray {
    val enhancer = Enhancer()
    enhancer.setSuperclass(target::class.java)
    enhancer.setCallback(MethodInterceptor { obj, method, args, proxy ->
        proxy.invoke(target, args)
    })
    enhancer.create()
    return enhancer.strategy.generate(enhancer)
}

