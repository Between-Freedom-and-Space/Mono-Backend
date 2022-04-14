package com.between_freedom_and_space.mono_backend.auth.security.models

import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import kotlinx.datetime.LocalDateTime

data class JWTParams(
    val secret: String,
    val audience: String,
    val issuer: String,
    val expiresAt: LocalDateTime = localDateTimeNow(),
    val subject: String? = null
)
