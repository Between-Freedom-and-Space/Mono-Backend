package com.between_freedom_and_space.mono_backend.auth.api.components

import com.between_freedom_and_space.mono_backend.auth.components.exceptions.AuthenticateException
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.InvalidTokenException
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.exceptionHandler
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.unauthorizedHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

internal fun Application.authExceptionHandling() {

    unauthorizedHandler<InvalidTokenException>()
    unauthorizedHandler<AuthenticateException>()
}