package com.between_freedom_and_space.mono_backend.util.extensions

import com.between_freedom_and_space.mono_backend.util.components.RequestValidator
import com.between_freedom_and_space.mono_backend.util.components.exception.ValidationException
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

private typealias CallPipeline = PipelineContext<Unit, ApplicationCall>

suspend inline fun <reified T: Any> CallPipeline.validateAndReceiveRequest(): T {
    val requestValidator by inject<RequestValidator>()
    val request = call.receive<T>()

    requestValidator.validateRequest(request)
        .takeIf { it.isNotEmpty() }
        .ifNotNull { violation ->
            val message = violation.first().message
            throw ValidationException(message)
        }

    return request
}

suspend inline fun <reified T: Any> CallPipeline.sendResponse(response: T) {
    call.respond(response)
}

suspend fun CallPipeline.sendEmptyResponse() {
    call.respond(Unit)
}

fun CallPipeline.getRequestHeader(name: String): String? {
    return call.request.header(name)
}

fun CallPipeline.appendResponseHeader(name: String, value: String) {
    call.response.header(name, value)
}

fun CallPipeline.appendResponseHeader(name: String, value: Int) {
    call.response.header(name, value)
}

fun CallPipeline.appendResponseHeader(name: String, value: Long) {
    call.response.header(name, value)
}

fun CallPipeline.getPathParameter(name: String): String? {
    return call.parameters[name]
}

fun CallPipeline.getQueryParameter(name: String): String? {
    return call.request.queryParameters[name]
}