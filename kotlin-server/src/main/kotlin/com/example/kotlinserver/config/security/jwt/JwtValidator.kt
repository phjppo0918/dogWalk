package com.example.kotlinserver.config.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.security.Key

@Component
class JwtValidator(private val key : Key) {

    fun getAuthentication(accessToken : String) : Authentication {
        val claims : Claims = parseClaims(accessToken)
        val authorities : Collection<GrantedAuthority> = listOf(claims["auth"].toString()).map(::SimpleGrantedAuthority)
        return UsernamePasswordAuthenticationToken(User(claims.subject, "", authorities), "", authorities);

    }

    fun validateToken(token : String) {
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
    }

    private fun parseClaims(token: String) : Claims =
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
}