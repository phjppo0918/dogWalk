package com.example.kotlinserver.config.security

import com.example.kotlinserver.config.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val userDetailService: UserDetailsService
) {


}