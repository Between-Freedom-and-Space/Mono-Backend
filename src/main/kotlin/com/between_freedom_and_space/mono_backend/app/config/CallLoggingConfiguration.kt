package com.between_freedom_and_space.mono_backend.app.config

import io.ktor.server.plugins.*
import io.ktor.server.plugins.callloging.CallLoggingConfig
import org.slf4j.event.Level

internal fun CallLoggingConfig.configure() {
    level = Level.DEBUG
}