package com.example.kotlinserver.config.security.jwt

data class JwtToken(
    val accessToken: String,
    val refreshToken: String,
    val grantType: String
)