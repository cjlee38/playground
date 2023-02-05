package com.example.httpplayground.connection

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class KeepAliveController {
    @GetMapping("/connection/hello")
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok("hello connection")
    }
}