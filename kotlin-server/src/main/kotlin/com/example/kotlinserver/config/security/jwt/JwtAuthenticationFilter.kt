package com.example.kotlinserver.config.security.jwt

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

const val TOKEN_TYPE : String = "Bearer"
@Component
class JwtAuthenticationFilter(
    @Value("\${jwt.access-header}") val accessTokenHeaderTag : String,
    private val jwtValidator: JwtValidator
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token : String = resolveToken(request)?: throw NoSuchElementException()

        val auth : Authentication = jwtValidator.getAuthentication(token)
        SecurityContextHolder.getContext().authentication = auth
    }

    private fun resolveToken(request: HttpServletRequest) : String? {
        val token : String = request.getHeader(accessTokenHeaderTag)
        if(StringUtils.hasText(token) && token.startsWith(TOKEN_TYPE)) {
            return token.substring(7)
        }
        return null
    }
}