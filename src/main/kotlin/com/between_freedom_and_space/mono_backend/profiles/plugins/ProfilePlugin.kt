package com.between_freedom_and_space.mono_backend.profiles.plugins

import com.between_freedom_and_space.mono_backend.profiles.routing.profilesRouting
import com.between_freedom_and_space.mono_backend.profiles.sessions.plugins.addSessionPlugin
import io.ktor.server.application.*

fun Application.addProfilePlugin() {
    // Internal plugins
    addSessionPlugin()

    // Components
    profilesRouting()
}