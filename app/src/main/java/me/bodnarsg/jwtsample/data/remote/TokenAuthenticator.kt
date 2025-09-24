package me.bodnarsg.jwtsample.data.remote

import android.util.Log
import me.bodnarsg.jwtsample.domain.repository.TokenStorageRepository
import me.bodnarsg.jwtsample.domain.repository.AuthRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Provider

class TokenAuthenticator @Inject constructor(
    private val tokenStorageRepository: TokenStorageRepository,
    private val authRepositoryProvider: Provider<AuthRepository>
) : Authenticator {

    private val isRefreshing = AtomicBoolean(false)

    override fun authenticate(route: Route?, response: Response): Request? {
        if (responseCount(response) >= 2) {
            Log.w(TAG, "authenticate: too many attempts -> give up")
            return null
        }

        if (response.code != 401) return null

        val refreshToken = tokenStorageRepository.getRefreshToken() ?: return null

        if (isRefreshing.compareAndSet(false, true)) {
            try {
                val refreshResult = runBlocking {
                    authRepositoryProvider.get().refreshToken(refreshToken)
                }
                if (refreshResult.isFailure) {
                    Log.w(TAG, "refresh failed: ${refreshResult.exceptionOrNull()?.message}")
                    tokenStorageRepository.clearTokens()
                    return null
                }
                val newTokens = refreshResult.getOrNull()!!
                tokenStorageRepository.saveAccessToken(newTokens.accessToken)
                tokenStorageRepository.saveRefreshToken(newTokens.refreshToken)
                Log.d(TAG, "refresh success -> retry original request")
            } finally {
                isRefreshing.set(false)
            }
        } else {
            while (isRefreshing.get()) {
                try { Thread.sleep(50) } catch (_: InterruptedException) { break }
            }
        }

        val newAccess = tokenStorageRepository.getAccessToken() ?: return null
        return response.request.newBuilder()
            .header("Authorization", "Bearer $newAccess")
            .build()
    }

    private fun responseCount(response: Response): Int {
        var count = 1
        var current = response.priorResponse
        while (current != null) {
            count++
            current = current.priorResponse
        }
        return count
    }

    companion object { private const val TAG = "TokenAuthenticator" }
}

