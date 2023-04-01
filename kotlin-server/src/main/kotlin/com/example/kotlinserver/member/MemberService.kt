package com.example.kotlinserver.member

import com.example.kotlinserver.member.dto.MemberResponse
import com.example.kotlinserver.member.dto.SignUpRequest
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class MemberService(private val memberRepository: MemberRepository) {
    @Transactional
    fun save(dto: SignUpRequest) {
        memberRepository.save(dto.toEntity())
    }

    fun findAllByNicknameContaining(nickname: String) : List<MemberResponse> =
        memberRepository.findAllByNicknameContaining(nickname).map { MemberResponse(it) }.toList()
}