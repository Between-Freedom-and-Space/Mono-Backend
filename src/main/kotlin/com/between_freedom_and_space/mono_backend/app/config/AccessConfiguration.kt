package com.between_freedom_and_space.mono_backend.app.config

import com.between_freedom_and_space.mono_backend.access.components.plugin.AccessPlugin

internal fun AccessPlugin.Configuration.configure() {
    allowAllRequests = false
    enableLogging = true
    defaultRoutingAccessor = null
}