package me.bodnarsg.jwtsample.data.remote

import me.bodnarsg.jwtsample.data.dto.auth.UserResponse
import retrofit2.http.GET

interface UserAPI {
    @GET("protected/me")
    suspend fun getMe(): UserResponse
}