package com.between_freedom_and_space.mono_backend.app

import com.between_freedom_and_space.mono_backend.access.plugin.addAccessPlugin
import com.between_freedom_and_space.mono_backend.auth.components.plugin.AuthenticatePlugin
import com.between_freedom_and_space.mono_backend.auth.plugins.addAuthPlugin
import com.between_freedom_and_space.mono_backend.common.components.BaseExceptionHandler
import com.between_freedom_and_space.mono_backend.common.plugins.ExceptionHandlerPlugin
import com.between_freedom_and_space.mono_backend.posts.plugins.addPostsPlugin
import com.between_freedom_and_space.mono_backend.profiles.plugins.addProfilePlugin
import com.between_freedom_and_space.mono_backend.recommendations.plugin.addRecommendationsPlugin
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Application.main() {
    // Plugins
//    install(CallLogging)
//    install(Authentication)
//    install(CORS)
//    install(MicrometerMetrics)
//    install(ExceptionHandlerPlugin) {
//        enableLogging = true
//        defaultHandler = BaseExceptionHandler()
//    }

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    // Components
//    addAccessPlugin()
    addAuthPlugin()
    addPostsPlugin()
    addProfilePlugin()
//    addRecommendationsPlugin()
}