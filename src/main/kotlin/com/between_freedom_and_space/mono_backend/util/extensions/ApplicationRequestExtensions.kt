package com.between_freedom_and_space.mono_backend.util.extensions

import io.ktor.server.request.*

fun ApplicationRequest.getPathParameter(name: String): String? {
    return call.parameters[name]
}

fun ApplicationRequest.getQueryParameter(name: String): String? {
    return queryParameters[name]
}