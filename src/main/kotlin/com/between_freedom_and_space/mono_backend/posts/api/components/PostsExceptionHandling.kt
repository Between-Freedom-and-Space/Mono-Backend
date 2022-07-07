package com.between_freedom_and_space.mono_backend.posts.api.routing

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.exceptionHandler
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.InvalidPostException
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.PostNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

internal fun Application.postsExceptionHandling() {

    exceptionHandler<InvalidPostException> { call, exception ->
        exception as InvalidPostException
        val response = Response.badRequest(message = exception.message)
        call.respond(HttpStatusCode.BadRequest, response)
    }

    exceptionHandler<PostNotFoundException> { call, exception ->
        exception as PostNotFoundException
        val response = Response.notFound(message = exception.message)
        call.respond(HttpStatusCode.NotFound, response)
    }
}