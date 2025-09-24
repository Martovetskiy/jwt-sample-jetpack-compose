package me.bodnarsg.jwtsample.domain.usecase

import me.bodnarsg.jwtsample.domain.model.User
import me.bodnarsg.jwtsample.domain.repository.UserRepository
import javax.inject.Inject

class GetMeUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<User>{
        return userRepository.getMe().fold(
            onSuccess = {
                Result.success(it)
            },
            onFailure = {
                Result.failure(it)
            }
        )
    }
}