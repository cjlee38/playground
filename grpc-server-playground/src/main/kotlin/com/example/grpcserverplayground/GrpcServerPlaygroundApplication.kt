package com.example.grpcserverplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrpcServerPlaygroundApplication

fun main(args: Array<String>) {
	runApplication<GrpcServerPlaygroundApplication>(*args)
}
