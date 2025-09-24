package me.bodnarsg.jwtsample.data.remote

import me.bodnarsg.jwtsample.data.dto.auth.LoginRequest
import me.bodnarsg.jwtsample.data.dto.auth.RefreshRequest
import me.bodnarsg.jwtsample.data.dto.auth.RegisterRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val api: AuthAPI
) {
    suspend fun login(email: String, password: String) =
        api.login(LoginRequest(email, password))

    suspend fun register(email: String, password: String, name: String) =
        api.register(RegisterRequest(email, password, name))

    suspend fun refreshToken(refreshToken: String) =
        api.refresh(RefreshRequest(refreshToken))

    suspend fun getMe() = api.getMe()
}