package com.example.remoteserver.remotes

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RemoteController {
    @GetMapping("/users/{id}")
    fun user(@PathVariable id: Long): ResponseEntity<String> {
        Thread.sleep(1000L)
        return ResponseEntity.ok("user : $id")
    }

    @GetMapping("/items/{id}")
    fun item(@PathVariable id: Long): ResponseEntity<String> {
        Thread.sleep(2000L)
        return ResponseEntity.ok("item : $id")
    }
}