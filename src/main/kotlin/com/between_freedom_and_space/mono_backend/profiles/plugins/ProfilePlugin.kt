package com.between_freedom_and_space.mono_backend.profiles.plugins

import com.between_freedom_and_space.mono_backend.auth.components.plugin.AuthenticatePlugin
import com.between_freedom_and_space.mono_backend.profiles.api.routing.profilesActionRouting
import com.between_freedom_and_space.mono_backend.profiles.api.routing.profilesExceptionHandling
import com.between_freedom_and_space.mono_backend.profiles.api.routing.profilesInformationRouting
import com.between_freedom_and_space.mono_backend.profiles.api.routing.profilesInteractionRouting
import io.ktor.server.application.*

fun Application.addProfilePlugin() {
    profilesInformationRouting()
    profilesActionRouting()
    profilesInteractionRouting()

    profilesExceptionHandling()
}