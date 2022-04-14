package com.between_freedom_and_space.mono_backend.recommendations.plugin

import com.between_freedom_and_space.mono_backend.auth.components.plugin.AuthenticatePlugin
import io.ktor.server.application.*

fun Application.addRecommendationsPlugin() {
    install(AuthenticatePlugin)
}