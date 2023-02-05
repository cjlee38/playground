package com.example.httpplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HttpPlaygroundApplication

fun main(args: Array<String>) {
	runApplication<HttpPlaygroundApplication>(*args)
}
