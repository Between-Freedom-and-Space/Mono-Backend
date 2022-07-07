package com.between_freedom_and_space.mono_backend.posts.internal.comments.api.routing

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.exceptionHandler
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.exceptions.CommentNotFoundException
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.exceptions.InvalidCommentException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

internal fun Application.commentsExceptionHandling() {

    exceptionHandler<CommentNotFoundException> { call, exception ->
        exception as CommentNotFoundException
        val response = Response.notFound(message = exception.message)
        call.respond(HttpStatusCode.NotFound, response)
    }

    exceptionHandler<InvalidCommentException> { call, exception ->
        exception as InvalidCommentException
        val response = Response.notFound(message = exception.message)
        call.respond(response)
    }
}