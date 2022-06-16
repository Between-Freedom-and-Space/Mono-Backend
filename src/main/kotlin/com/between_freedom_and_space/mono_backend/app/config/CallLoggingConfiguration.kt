package com.between_freedom_and_space.mono_backend.app.config

import io.ktor.server.plugins.*
import org.slf4j.event.Level

internal fun CallLogging.Configuration.configure() {
    level = Level.INFO
}