package com.between_freedom_and_space.mono_backend.popular.plugin

import com.between_freedom_and_space.mono_backend.auth.components.plugin.AuthenticatePlugin
import com.between_freedom_and_space.mono_backend.popular.api.components.popularRoutingAccessors
import com.between_freedom_and_space.mono_backend.popular.api.routing.lastCreatedRouting
import com.between_freedom_and_space.mono_backend.popular.api.routing.popularRouting
import io.ktor.server.application.*

fun Application.addPopularPlugin() {
    popularRouting()
    lastCreatedRouting()

    popularRoutingAccessors()
}