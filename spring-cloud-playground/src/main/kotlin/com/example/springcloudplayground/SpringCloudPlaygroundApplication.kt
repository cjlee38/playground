package com.example.springcloudplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCloudPlaygroundApplication

fun main(args: Array<String>) {
	runApplication<SpringCloudPlaygroundApplication>(*args)
}
