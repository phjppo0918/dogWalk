package com.example.kotlinserver.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MemberRepository : JpaRepository<Member, UUID> {
    fun findAllByNicknameContaining(nickname: String): List<Member>
}