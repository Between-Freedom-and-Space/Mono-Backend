package com.between_freedom_and_space.mono_backend.posts.internal.tags.services.exception

import io.ktor.server.plugins.*

class TagNotFoundException(message: String): RuntimeException(message)