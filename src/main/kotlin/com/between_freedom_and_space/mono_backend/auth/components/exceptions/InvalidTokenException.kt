package com.between_freedom_and_space.mono_backend.auth.components.exceptions

class InvalidTokenException(
    message: String,
    val token: String,
): RuntimeException(message)