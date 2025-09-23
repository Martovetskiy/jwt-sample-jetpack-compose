package me.bodnarsg.jwtsample.domain.repository

import me.bodnarsg.jwtsample.domain.model.AuthToken
import me.bodnarsg.jwtsample.domain.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<AuthToken>
    suspend fun registration(username: String, password: String, name: String): Result<User>
    suspend fun refreshToken(refreshToken: String): Result<AuthToken>
    suspend fun logout(): Result<Unit>
}