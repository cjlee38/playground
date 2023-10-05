package com.example.webfluxplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@SpringBootApplication
class WebfluxPlaygroundApplication

fun main(args: Array<String>) {
    Executors.newWorkStealingPool()
    runApplication<WebfluxPlaygroundApplication>(*args)
}
