package me.bodnarsg.jwtsample.domain.usecase

import me.bodnarsg.jwtsample.domain.repository.TokenStorageRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val tokenStorageRepository: TokenStorageRepository
) {
    operator fun invoke() {
        tokenStorageRepository.clearTokens()
    }
}