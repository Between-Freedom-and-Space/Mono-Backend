package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.routing

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.exceptionHandler
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.exceptions.InvalidReactionException
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.exceptions.ReactionNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

internal fun Application.reactionsExceptionHandling() {

//    exceptionHandler<InvalidReactionException> { call, exception ->
//        exception as InvalidReactionException
//        val response = Response.badRequest(message = exception.message)
//        call.respond(HttpStatusCode.BadRequest, response)
//    }
//
//    exceptionHandler<ReactionNotFoundException> { call, exception ->
//        exception as ReactionNotFoundException
//        val response = Response.notFound(message = exception.message)
//        call.respond(HttpStatusCode.NotFound, response)
//    }
}