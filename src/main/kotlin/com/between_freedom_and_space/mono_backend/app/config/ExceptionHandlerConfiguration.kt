package com.between_freedom_and_space.mono_backend.app.config

import com.between_freedom_and_space.mono_backend.common.components.BaseExceptionHandler
import com.between_freedom_and_space.mono_backend.common.plugins.ExceptionHandlerPlugin

internal fun ExceptionHandlerPlugin.Configuration.configure() {
    enableLogging = true
    defaultHandler = BaseExceptionHandler()
}