package com.between_freedom_and_space.mono_backend.common.plugins

import io.ktor.server.application.*
import kotlin.reflect.KClass

interface ExceptionInterceptor {

    suspend fun handle(
        exception: Exception, call: ApplicationCall,
        handlers: Map<KClass<*>, ExceptionHandler>,
        defaultHandler: ExceptionHandler?
    )
}