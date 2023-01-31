package com.study.querydslplayground.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Team(
    val name: String,

    @OneToMany(mappedBy = "team")
    val members: MutableList<Member> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
) {
    override fun toString(): String {
        return "Team(name='$name', id=$id)"
    }
}