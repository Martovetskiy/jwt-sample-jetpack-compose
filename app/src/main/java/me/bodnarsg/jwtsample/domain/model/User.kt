package me.bodnarsg.jwtsample.domain.model

import java.util.UUID

data class User(
    val uuid: UUID,
    val username: String,
    val name: String,
)
