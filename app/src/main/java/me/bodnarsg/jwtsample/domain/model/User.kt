package me.bodnarsg.jwtsample.domain.model

import java.util.UUID

data class User(
    val uuid: UUID,
    val email: String,
    val name: String,
)
