package com.example.kotlinserver.member

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*

@Entity
class Member (
    @Column(unique = true) val email: String,
    val nickname: String,
    val userPassword: String,
    @Id val id: UUID = UUID.randomUUID(),
    @ElementCollection(fetch = FetchType.EAGER) @Enumerated(EnumType.STRING)
    private val role: List<Role> = listOf(Role.ROLE_USER)
) : UserDetails {
    override fun getAuthorities(): List<SimpleGrantedAuthority> =
        role.map(Role::name).map(::SimpleGrantedAuthority).toList()
    override fun getPassword(): String = userPassword
    override fun getUsername() : String = email
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}

