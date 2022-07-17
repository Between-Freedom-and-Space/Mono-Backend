package com.between_freedom_and_space.mono_backend.access.plugin

import com.between_freedom_and_space.mono_backend.access.api.components.accessRoutingAccessors
import com.between_freedom_and_space.mono_backend.access.api.routing.accessExceptionHandling
import com.between_freedom_and_space.mono_backend.access.api.routing.accessInformationRouting
import com.between_freedom_and_space.mono_backend.access.api.routing.accessInteractionRouting
import io.ktor.server.application.*

fun Application.addAccessPlugin() {
    accessInformationRouting()
    accessInteractionRouting()

    accessExceptionHandling()
    accessRoutingAccessors()
}