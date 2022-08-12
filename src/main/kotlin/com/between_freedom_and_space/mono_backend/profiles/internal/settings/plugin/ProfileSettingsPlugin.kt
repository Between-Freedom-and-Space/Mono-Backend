package com.between_freedom_and_space.mono_backend.profiles.internal.settings.plugin

import com.between_freedom_and_space.mono_backend.profiles.internal.settings.api.components.profileSettingsExceptionHandler
import com.between_freedom_and_space.mono_backend.profiles.internal.settings.api.components.profileSettingsRoutingAccessors
import com.between_freedom_and_space.mono_backend.profiles.internal.settings.api.routing.profileSettingsInformationRouting
import io.ktor.server.application.*

internal fun Application.addProfileSettingsPlugin() {
    profileSettingsInformationRouting()

    profileSettingsRoutingAccessors()
    profileSettingsExceptionHandler()
}