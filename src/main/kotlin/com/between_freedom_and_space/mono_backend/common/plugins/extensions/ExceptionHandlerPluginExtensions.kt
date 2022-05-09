package com.between_freedom_and_space.mono_backend.common.plugins.extensions

import com.between_freedom_and_space.mono_backend.common.plugins.ExceptionHandler
import com.between_freedom_and_space.mono_backend.common.plugins.ExceptionHandlerPlugin
import io.ktor.server.application.*

inline fun <reified T: Exception> Application.exceptionHandler(noinline handler: ExceptionHandler): ExceptionHandlerPlugin {
    val plugin = pluginOrNull(ExceptionHandlerPlugin)
    if (plugin != null) {
        plugin.addHandler(handler, T::class)
        return plugin
    }

    return install(ExceptionHandlerPlugin) {
        handlers = mapOf(T::class to handler)
    }
}