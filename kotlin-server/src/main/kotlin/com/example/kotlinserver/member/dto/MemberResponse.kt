package com.example.kotlinserver.member.dto

import com.example.kotlinserver.member.Member
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class MemberResponse(
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) val member: Member,
    val id: UUID = member.id,
    val email: String = member.email,
    val nickname: String = member.nickname
)

