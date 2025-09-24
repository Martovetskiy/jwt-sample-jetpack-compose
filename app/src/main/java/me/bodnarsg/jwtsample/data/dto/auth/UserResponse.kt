package me.bodnarsg.jwtsample.data.dto.auth

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserResponse(
    val uuid: UUID,
    val email: String,
    val name: String,
)
