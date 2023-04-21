package com.between_freedom_and_space.mono_backend.common.plugins.extensions

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.plugins.ExceptionHandlerPlugin
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.KtorDsl
import io.ktor.util.pipeline.*
import kotlin.reflect.KClass

@KtorDsl
inline fun <reified T : Exception> Application.exceptionHandler(
    noinline handler: suspend (ApplicationCall, Exception) -> Unit
): ExceptionHandlerPlugin {
    val plugin = pluginOrNull(ExceptionHandlerPlugin)
    if (plugin != null) {
        plugin.setHandler(handler, T::class)
        return plugin
    }

    return install(ExceptionHandlerPlugin) {
        handlers = mapOf(T::class to handler)
    }
}

@KtorDsl
inline fun <reified T : Exception> Application.badRequestHandler(): ExceptionHandlerPlugin {
    return exceptionHandler<T> { call, exception ->
        exception as T
        val response = Response.badRequest(message = exception.message)
        call.respond(HttpStatusCode.BadRequest, response)
    }
}

@KtorDsl
inline fun <reified T : Exception> Application.forbiddenHandler(): ExceptionHandlerPlugin {
    return exceptionHandler<T> { call, exception ->
        exception as T
        val response = Response.forbidden(message = exception.message)
        call.respond(HttpStatusCode.Forbidden, response)
    }
}

@KtorDsl
inline fun <reified T : Exception> Application.notFoundHandler(): ExceptionHandlerPlugin {
    return exceptionHandler<T> { call, exception ->
        exception as T
        val response = Response.notFound(message = exception.message)
        call.respond(HttpStatusCode.NotFound, response)
    }
}

@KtorDsl
inline fun <reified T : Exception> Application.unauthorizedHandler(): ExceptionHandlerPlugin {
    return exceptionHandler<T> { call, exception ->
        exception as T
        val response = Response.unauthorized(message = exception.message)
        call.respond(HttpStatusCode.Unauthorized, response)
    }
}

@KtorDsl
inline fun <reified T : Exception> Application.internalServerErrorHandler(): ExceptionHandlerPlugin {
    return exceptionHandler<T> { call, exception ->
        exception as T
        val response = Response.internalServerError(message = exception.message)
        call.respond(HttpStatusCode.InternalServerError, response)
    }
}