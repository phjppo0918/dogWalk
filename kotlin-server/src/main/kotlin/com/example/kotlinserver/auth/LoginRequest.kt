package com.example.kotlinserver.auth

data class LoginRequest(
    val email: String,
    val password: String
)
