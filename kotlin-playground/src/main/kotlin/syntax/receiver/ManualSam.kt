package syntax.receiver

import java.util.function.IntSupplier
import java.util.function.Supplier

fun main() {
    val receiver = Receiver()
    val primitiveSupplier = IntSupplier { 10 }
    val wrapperSupplier = Supplier<Int> { 10 }
    val ambiguousSupplier = { 10 }

    receiver.invokeThis(wrapperSupplier)
    receiver.invokeThis(primitiveSupplier)
//    receiver.invokeThis(ambiguousSupplier) // 컴파일 에러가 발생한다.
}
