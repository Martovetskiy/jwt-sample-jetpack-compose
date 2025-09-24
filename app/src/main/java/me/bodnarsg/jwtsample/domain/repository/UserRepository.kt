package me.bodnarsg.jwtsample.domain.repository

import me.bodnarsg.jwtsample.domain.model.User

interface UserRepository {
    suspend fun getMe(): Result<User>
}