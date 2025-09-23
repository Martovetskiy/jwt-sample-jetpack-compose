package me.bodnarsg.jwtsample.domain.repository

interface TokenStorageRepository {
    fun saveAccessToken(token: String)
    fun saveRefreshToken(token: String)
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun clearTokens()
}