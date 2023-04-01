package com.example.kotlinserver.config.security.jwt

import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.security.Key

@Configuration
class JwtKeyContext(
    @Value("\${jwt.secret}")
    private val secretKey: String
) {
    @Bean
    fun key(): Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
}