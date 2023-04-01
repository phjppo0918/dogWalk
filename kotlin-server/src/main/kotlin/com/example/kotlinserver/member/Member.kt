package com.example.kotlinserver.member

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Member(
    @Column(unique = true) val email: String,
    val nickname: String,
    val password: String,
    @Id val id: UUID = UUID.randomUUID()
)

