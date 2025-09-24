package me.bodnarsg.jwtsample.data.repository

import me.bodnarsg.jwtsample.data.remote.UserRemoteDataSource
import me.bodnarsg.jwtsample.domain.model.User
import me.bodnarsg.jwtsample.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
): UserRepository {
    override suspend fun getMe(): Result<User> {
        return try {
            val response = userRemoteDataSource.getMe()
            val user = User(
                uuid = response.uuid,
                email = response.email,
                name = response.name
            )
            Result.success(user)
        }
        catch (e: Exception){
            Result.failure(e)
        }
    }

}