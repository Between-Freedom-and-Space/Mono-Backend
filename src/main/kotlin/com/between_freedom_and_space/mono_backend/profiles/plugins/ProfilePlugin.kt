package com.between_freedom_and_space.mono_backend.profiles.plugins

import com.between_freedom_and_space.mono_backend.profiles.api.components.profilesRoutingAccessors
import com.between_freedom_and_space.mono_backend.profiles.api.routing.profilesActionRouting
import com.between_freedom_and_space.mono_backend.profiles.api.components.profilesExceptionHandling
import com.between_freedom_and_space.mono_backend.profiles.api.routing.profilesInformationRouting
import com.between_freedom_and_space.mono_backend.profiles.api.routing.profilesInteractionRouting
import com.between_freedom_and_space.mono_backend.profiles.internal.settings.plugin.addProfileSettingsPlugin
import io.ktor.server.application.*

fun Application.addProfilePlugin() {
    profilesInformationRouting()
    profilesActionRouting()
    profilesInteractionRouting()

    profilesExceptionHandling()
    profilesRoutingAccessors()

    addProfileSettingsPlugin()
}