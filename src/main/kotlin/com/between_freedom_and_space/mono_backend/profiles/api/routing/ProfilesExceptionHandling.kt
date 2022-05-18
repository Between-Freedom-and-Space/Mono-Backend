package com.between_freedom_and_space.mono_backend.profiles.api.routing

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.exceptionHandler
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.CreationProfileException
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.InvalidProfileException
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.ProfileNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

internal fun Application.profilesExceptionHandling() {

//    exceptionHandler<CreationProfileException> { call, exception ->
//        exception as CreationProfileException
//        val response = Response.badRequest(message = exception.message)
//        call.respond(HttpStatusCode.BadRequest, response)
//    }
//
//    exceptionHandler<InvalidProfileException> { call, exception ->
//        exception as InvalidProfileException
//        val response = Response.badRequest(message = exception.message)
//        call.respond(HttpStatusCode.BadRequest, response)
//    }
//
//    exceptionHandler<ProfileNotFoundException> { call, exception ->
//        exception as ProfileNotFoundException
//        val response = Response.notFound(message = exception.message)
//        call.respond(HttpStatusCode.NotFound, response)
//    }
}