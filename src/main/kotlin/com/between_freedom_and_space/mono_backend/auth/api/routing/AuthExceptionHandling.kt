package com.between_freedom_and_space.mono_backend.auth.api.routing

import com.between_freedom_and_space.mono_backend.auth.components.exceptions.AuthenticateException
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.InvalidTokenException
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.exceptionHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

internal fun Application.authExceptionHandling() {

//    exceptionHandler<InvalidTokenException> { call, exception ->
//        exception as InvalidTokenException
//        val response = Response.unauthorized(message = exception.message)
//        call.respond(HttpStatusCode.Unauthorized, response)
//    }
//
//    exceptionHandler<AuthenticateException> { call, exception ->
//        exception as AuthenticateException
//        val response = Response.unauthorized(message = exception.message)
//        call.respond(HttpStatusCode.Unauthorized, response)
//    }
}