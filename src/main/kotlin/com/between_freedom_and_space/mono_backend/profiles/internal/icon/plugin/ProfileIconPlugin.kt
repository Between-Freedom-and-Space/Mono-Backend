package com.between_freedom_and_space.mono_backend.profiles.internal.icon.plugin

import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.components.profileIconExceptionHandler
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.components.profileIconRoutingAccessors
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.routing.profileIconInformationRouting
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.routing.profileIconInteractionRouting
import io.ktor.server.application.*

internal fun Application.addProfileIconPlugin() {
    profileIconInteractionRouting()
    profileIconInformationRouting()

    profileIconExceptionHandler()
    profileIconRoutingAccessors()
}