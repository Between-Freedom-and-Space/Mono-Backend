package com.between_freedom_and_space.mono_backend.access.api.routing

import com.between_freedom_and_space.mono_backend.access.components.exceptions.AccessException
import com.between_freedom_and_space.mono_backend.access.service.exception.InvalidRoleException
import com.between_freedom_and_space.mono_backend.access.service.exception.InvalidRuleException
import com.between_freedom_and_space.mono_backend.access.service.exception.RoleNotFoundException
import com.between_freedom_and_space.mono_backend.access.service.exception.RuleNotFoundException
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

    exceptionHandler<InvalidRoleException> { call, exception ->
        exception as InvalidRoleException
        val response = Response.badRequest(message = exception.message)
        call.respond(HttpStatusCode.BadRequest, response)
    }

    exceptionHandler<InvalidRuleException> { call, exception ->
        exception as InvalidRuleException
        val response = Response.badRequest(message = exception.message)
        call.respond(HttpStatusCode.BadRequest, response)
    }

    exceptionHandler<RoleNotFoundException> { call, exception ->
        exception as RoleNotFoundException
        val response = Response.notFound(message = exception.message)
        call.respond(HttpStatusCode.NotFound, response)
    }

    exceptionHandler<RuleNotFoundException> { call, exception ->
        exception as RuleNotFoundException
        val response = Response.notFound(message = exception.message)
        call.respond(HttpStatusCode.NotFound, response)
    }
}