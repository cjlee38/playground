package com.example.webfluxplayground.r2dbc

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : ReactiveCrudRepository<Member, Long>