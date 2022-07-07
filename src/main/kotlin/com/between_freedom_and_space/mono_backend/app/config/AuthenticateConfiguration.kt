package com.between_freedom_and_space.mono_backend.app.config

import com.between_freedom_and_space.mono_backend.auth.components.plugin.AuthenticatePlugin
import com.between_freedom_and_space.mono_backend.auth.components.plugin.config.IgnoredPath

internal fun AuthenticatePlugin.Configuration.configure() {
    enableLogging = true
    ignoredPaths = mutableListOf(
        object: IgnoredPath {
            override val path = "/auth"
            override val isPrefix = true
        },
        object: IgnoredPath {
            override val path = "/metrics"
            override val isPrefix = false
        },
    )
}