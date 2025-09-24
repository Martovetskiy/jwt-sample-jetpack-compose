package me.bodnarsg.jwtsample.data.remote

import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val api: UserAPI
) {
    suspend fun getMe() =
        api.getMe()
}