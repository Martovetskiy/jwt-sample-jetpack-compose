package me.bodnarsg.jwtsample.domain.usecase

import me.bodnarsg.jwtsample.domain.repository.AuthRepository
import me.bodnarsg.jwtsample.domain.repository.TokenStorageRepository
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    val authRepository: AuthRepository,
    val tokenStorageRepository: TokenStorageRepository
) {
    suspend operator fun invoke(username: String, password: String, name: String): Result<Unit> {
        val registrationResult = authRepository.registration(username, password, name)
        if (registrationResult.isFailure) {
            return Result.failure(registrationResult.exceptionOrNull() ?: Exception("Unknown error during registration"))
        }

        return authRepository.login(username, password).fold(
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