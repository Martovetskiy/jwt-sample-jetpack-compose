package me.bodnarsg.jwtsample.data.repository

import me.bodnarsg.jwtsample.data.dto.auth.TokenResponse
import me.bodnarsg.jwtsample.data.dto.auth.UserResponse
import me.bodnarsg.jwtsample.data.remote.AuthRemoteDataSource
import me.bodnarsg.jwtsample.domain.model.AuthToken
import me.bodnarsg.jwtsample.domain.model.User
import me.bodnarsg.jwtsample.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
): AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<AuthToken> {
        return try{
            val response: TokenResponse = remoteDataSource.login(email, password)
            val result = AuthToken(
                accessToken = response.accessToken,
                refreshToken = response.refreshToken,
                expiresIn = response.expiresIn
            )
            Result.success(result)
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun registration(
        email: String,
        password: String,
        name: String
    ): Result<User> {
        return try{
            val response: UserResponse = remoteDataSource.register(email, password, name)
            val result = User(
                uuid = response.uuid,
                email = response.email,
                name = response.name
            )
            Result.success(result)
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun refreshToken(refreshToken: String): Result<AuthToken> {
        return try{
            val response: TokenResponse = remoteDataSource.refreshToken(refreshToken)
            val result = AuthToken(
                accessToken = response.accessToken,
                refreshToken = response.refreshToken,
                expiresIn = response.expiresIn
            )
            Result.success(result)
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }
}