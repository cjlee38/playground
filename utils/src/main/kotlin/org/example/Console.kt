package org.example

fun errorln(message: String) {
    System.err.println(message)
}

fun errorln(message: Any) = errorln(message.toString())