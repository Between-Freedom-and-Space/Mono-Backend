package com.between_freedom_and_space.mono_backend.common.plugins.impl

import com.between_freedom_and_space.mono_backend.common.plugins.ExceptionHandler
import com.between_freedom_and_space.mono_backend.common.plugins.ExceptionInterceptor
import io.ktor.server.application.*
import kotlin.reflect.KClass

class DefaultExceptionInterceptor: ExceptionInterceptor {

    override fun handle(
        exception: Exception, call: ApplicationCall,
        handlers: Map<KClass<*>, ExceptionHandler>,
        defaultHandler: ExceptionHandler?
    ) {
        val exceptionClass = exception::class
        val targetHandler = handlers[exceptionClass]
        targetHandler?.invoke(call)

        if (targetHandler == null) {
            defaultHandler?.invoke(call) ?: throw exception
        }
    }
}