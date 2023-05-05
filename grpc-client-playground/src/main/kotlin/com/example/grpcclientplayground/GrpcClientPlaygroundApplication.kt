package com.example.grpcclientplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrpcClientPlaygroundApplication

fun main(args: Array<String>) {
    runApplication<GrpcClientPlaygroundApplication>(*args)
}
