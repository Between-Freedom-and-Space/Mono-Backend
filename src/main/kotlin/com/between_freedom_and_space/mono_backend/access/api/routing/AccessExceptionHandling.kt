package com.between_freedom_and_space.mono_backend.access.api.routing

import com.between_freedom_and_space.mono_backend.access.components.exceptions.AccessException
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.exceptionHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

internal fun Application.accessExceptionHandling() {

    exceptionHandler<AccessException> { call, exception ->
        exception as AccessException
        val response = Response.forbidden(message = exception.message)
        call.respond(HttpStatusCode.Forbidden, response)
    }
}