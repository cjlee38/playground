package com.example.httpplayground.connection.scope

import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope
import java.util.UUID

@Component
@RequestScope
class RandomNumber(
    val id: UUID = UUID.randomUUID()
)
