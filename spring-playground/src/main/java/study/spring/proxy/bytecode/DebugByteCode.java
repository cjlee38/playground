package study.spring.proxy.bytecode;

public class DebugByteCode {
}

/*
fun <T : Any> generatorBytecodeByCglib(target: T): ByteArray {
    val enhancer = Enhancer()
    enhancer.setSuperclass(target::class.java)
    enhancer.setCallback(MethodInterceptor { obj, method, args, proxy ->
        proxy.invoke(target, args)
    })
    enhancer.create()
    return enhancer.strategy.generate(enhancer)
}
 */
