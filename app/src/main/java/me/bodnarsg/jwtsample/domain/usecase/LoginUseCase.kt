package me.bodnarsg.jwtsample.domain.usecase

import me.bodnarsg.jwtsample.domain.repository.AuthRepository
import me.bodnarsg.jwtsample.domain.repository.TokenStorageRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenStorageRepository: TokenStorageRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        val loginResult = authRepository.login(email, password)
        if (loginResult.isFailure) {
            return Result.failure(loginResult.exceptionOrNull() ?: Exception("Unknown error during login"))
        }

        return loginResult.fold(
            onSuccess = { authToken ->
                tokenStorageRepository.saveAccessToken(authToken.accessToken)
                tokenStorageRepository.saveRefreshToken(authToken.refreshToken)
                Result.success(Unit)
            },
            onFailure = { error ->
                Result.failure(error)
            }
        )
    }
}