package me.bodnarsg.jwtsample.data.dto.auth

data class LoginRequest(
    val email: String,
    val password: String
)
