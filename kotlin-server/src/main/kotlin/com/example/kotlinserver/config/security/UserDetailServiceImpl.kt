package com.example.kotlinserver.config.security

import com.example.kotlinserver.member.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl(private val memberRepository: MemberRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails =
        memberRepository.findByEmail(username)?: throw UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.");
}