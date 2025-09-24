package me.bodnarsg.jwtsample.data.repository

import android.content.SharedPreferences
import me.bodnarsg.jwtsample.domain.repository.TokenStorageRepository
import androidx.core.content.edit

class TokenStorageRepositoryImpl(
    private val sharedPreference: SharedPreferences
): TokenStorageRepository {
    override fun saveAccessToken(token: String) {
        sharedPreference.edit { putString("access_token", token) }
    }

    override fun saveRefreshToken(token: String) {
        sharedPreference.edit { putString("refresh_token", token) }
    }

    override fun getAccessToken(): String? {
        sharedPreference.getString("access_token", null)?.let {
            return it
        }
        return null
    }

    override fun getRefreshToken(): String? {
        sharedPreference.getString("refresh_token", null)?.let {
            return it
        }
        return null
    }

    override fun clearTokens() {
        sharedPreference.edit {
            remove("access_token")
            remove("refresh_token")
        }
    }
}