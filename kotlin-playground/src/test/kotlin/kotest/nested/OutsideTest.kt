package kotest.nested

import io.kotest.core.spec.style.AnnotationSpec

class NestedTest : AnnotationSpec() {
    @Test
    fun hello() {
        println("hi")
    }

    @Nested
    class InsideTest : AnnotationSpec() {
        @Test
        fun hi() {
            println("hellow")
        }
    }
}
