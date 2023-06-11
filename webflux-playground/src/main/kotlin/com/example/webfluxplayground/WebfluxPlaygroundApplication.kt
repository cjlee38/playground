package com.example.webfluxplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebfluxPlaygroundApplication

fun main(args: Array<String>) {
    runApplication<WebfluxPlaygroundApplication>(*args)
}
