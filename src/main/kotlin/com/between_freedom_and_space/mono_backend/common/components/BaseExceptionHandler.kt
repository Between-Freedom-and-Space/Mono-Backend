package com.between_freedom_and_space.mono_backend.common.components

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.plugins.ExceptionHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class BaseExceptionHandler: ExceptionHandler {

    override suspend fun invoke(call: ApplicationCall, exception: Exception) {
        val response = Response.internalServerError(message = exception.message)
        call.respond(HttpStatusCode.InternalServerError, response)
    }
}