package com.example.remoteserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RemoteServerApplication

fun main(args: Array<String>) {
    runApplication<RemoteServerApplication>(*args)
}
