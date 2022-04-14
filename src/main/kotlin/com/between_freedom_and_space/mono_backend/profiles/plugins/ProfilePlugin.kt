package com.between_freedom_and_space.mono_backend.profiles.plugins

import com.between_freedom_and_space.mono_backend.auth.components.plugin.AuthenticatePlugin
import com.between_freedom_and_space.mono_backend.profiles.api.routing.profilesRouting
import com.between_freedom_and_space.mono_backend.profiles.internal.sessions.plugins.addSessionPlugin
import com.between_freedom_and_space.mono_backend.profiles.internal.settings.plugins.addSettingsPlugin
import io.ktor.server.application.*

fun Application.addProfilePlugin() {
    install(AuthenticatePlugin)

    // Internal plugins
    addSessionPlugin()
    addSettingsPlugin()

    // Components
    profilesRouting()
}