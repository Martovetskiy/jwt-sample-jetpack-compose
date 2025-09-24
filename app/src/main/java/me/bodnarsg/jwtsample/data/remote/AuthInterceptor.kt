package me.bodnarsg.jwtsample.data.remote

import android.util.Log
import me.bodnarsg.jwtsample.domain.repository.TokenStorageRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenStorageRepository: TokenStorageRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val path = original.url.encodedPath

        if (path.startsWith("/auth/")) {
            return chain.proceed(original)
        }

        val token = tokenStorageRepository.getAccessToken()
        if (token.isNullOrBlank()) {
            Log.d("AuthInterceptor", "Access token is empty, proceed without header for $path")
            return chain.proceed(original)
        }

        if (original.header("Authorization") != null) {
            return chain.proceed(original)
        }

        val newRequest = original.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(newRequest)
    }
}
