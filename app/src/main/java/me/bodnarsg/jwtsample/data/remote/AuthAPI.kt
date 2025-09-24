package me.bodnarsg.jwtsample.data.remote

import me.bodnarsg.jwtsample.data.dto.auth.LoginRequest
import me.bodnarsg.jwtsample.data.dto.auth.RefreshRequest
import me.bodnarsg.jwtsample.data.dto.auth.RegisterRequest
import me.bodnarsg.jwtsample.data.dto.auth.TokenResponse
import me.bodnarsg.jwtsample.data.dto.auth.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthAPI {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): UserResponse

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): TokenResponse

    @POST("auth/refresh")
    suspend fun refresh(@Body request: RefreshRequest): TokenResponse

    @GET("protected/me")
    suspend fun getMe(): UserResponse
}