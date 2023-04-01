package com.example.kotlinserver.member.dto

import com.example.kotlinserver.member.Member

data class SignUpRequest(
    val email: String,
    val nickname: String,
    val password: String
) {
    fun toEntity(): Member = Member(email = email, nickname = nickname, password = password)
}

