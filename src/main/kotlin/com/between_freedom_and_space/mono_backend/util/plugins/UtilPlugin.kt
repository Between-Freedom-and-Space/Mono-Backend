package com.between_freedom_and_space.mono_backend.util.plugins

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.badRequestHandler
import com.between_freedom_and_space.mono_backend.common.plugins.extensions.exceptionHandler
import com.between_freedom_and_space.mono_backend.util.components.exception.ValidationException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*

fun Application.addUtilPlugin() {

    badRequestHandler<ValidationException>()
    badRequestHandler<ContentTransformationException>()
    badRequestHandler<CannotTransformContentToTypeException>()
}