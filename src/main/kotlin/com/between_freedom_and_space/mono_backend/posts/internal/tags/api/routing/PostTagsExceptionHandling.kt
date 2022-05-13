package com.between_freedom_and_space.mono_backend.posts.internal.tags.api.routing

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.exceptionHandler
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.exception.InvalidTagException
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.exception.TagNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

internal fun Application.postTagsExceptionHandling() {

    exceptionHandler<TagNotFoundException> { call, exception ->
        exception as TagNotFoundException
        val response = Response.notFound(message = exception.message)
        call.respond(HttpStatusCode.NotFound, response)
    }

    exceptionHandler<InvalidTagException> { call, exception ->
        exception as InvalidTagException
        val response = Response.badRequest(message = exception.message)
        call.respond(HttpStatusCode.BadRequest, response)
    }
}