package kotest.nested

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.AnnotationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class OutsideTest : AnnotationSpec() {
    init {
        println("constructed!!!")
    }
    @Test
    fun foo(@Autowired objectMapper: ObjectMapper) {
        val allStackTraces = Thread.getAllStackTraces()
        println(allStackTraces)
        println("foo")
    }

//    @Nested
//    class InsideTest : AnnotationSpec() {
//        @Test
//        fun bar() {
//            println("bar")
//        }
//    }
}
