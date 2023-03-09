package com.example.httpplayground.connection

import com.example.httpplayground.connection.scope.RandomNumber
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class KeepAliveController(
    private val randomNumber: RandomNumber
) {
    @GetMapping("/connection/hello")
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok("hello connection : ${randomNumber.id}")
    }
}
