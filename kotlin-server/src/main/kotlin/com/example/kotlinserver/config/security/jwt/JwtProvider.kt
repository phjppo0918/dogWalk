package com.example.kotlinserver.config.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

const val ACCESS_TOKEN_VALIDATION_SECOND: Long = 60L * 60 * 24 * 1000; // 1 Day
const val REFRESH_TOKEN_VALIDATION_SECOND: Long = 60L * 60 * 24 * 14 * 1000; // 14 Day
const val GRANT_TYPE: String = "Bearer"

@Component
class JwtProvider(private val key: Key) {
    private
    fun createJwtToken(authentication: Authentication): JwtToken {
        val authorities: String = authentication.authorities.map(GrantedAuthority::getAuthority)[0]
        val now: Long = Date().time

        val accessToken: String = Jwts.builder()
            .setSubject(authentication.name)
            .claim("auth", authorities)
            .setExpiration(Date(now + ACCESS_TOKEN_VALIDATION_SECOND))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        val refreshToken: String = Jwts.builder()
            .setExpiration(Date(now + REFRESH_TOKEN_VALIDATION_SECOND))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        return JwtToken(accessToken, refreshToken, GRANT_TYPE)
    }
}