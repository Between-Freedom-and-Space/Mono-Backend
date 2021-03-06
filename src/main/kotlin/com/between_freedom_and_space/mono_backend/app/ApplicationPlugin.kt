package com.between_freedom_and_space.mono_backend.app

import com.between_freedom_and_space.mono_backend.access.components.plugin.AccessPlugin
import com.between_freedom_and_space.mono_backend.access.plugin.addAccessPlugin
import com.between_freedom_and_space.mono_backend.app.config.configure
import com.between_freedom_and_space.mono_backend.app.config.configureDatabase
import com.between_freedom_and_space.mono_backend.auth.components.plugin.AuthenticatePlugin
import com.between_freedom_and_space.mono_backend.auth.plugins.addAuthPlugin
import com.between_freedom_and_space.mono_backend.common.plugins.ExceptionHandlerPlugin
import com.between_freedom_and_space.mono_backend.posts.plugins.addPostsPlugin
import com.between_freedom_and_space.mono_backend.profiles.plugins.addProfilePlugin
import com.between_freedom_and_space.mono_backend.util.plugins.addUtilPlugin
import io.ktor.server.application.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.plugins.*
import org.koin.core.KoinApplication

fun Application.main(koin: KoinApplication) {
    // Plugins
//    install(Authentication)
//    install(CORS)

    configureDatabase()

    install(CallLogging) { configure() }
    install(MicrometerMetrics) { configure(this@main) }
    install(ExceptionHandlerPlugin) { configure() }
    install(ContentNegotiation) { configure() }
    install(AuthenticatePlugin) { configure() }
    install(AccessPlugin) { configure() }

    // Components
    addAccessPlugin()
    addAuthPlugin()
    addPostsPlugin()
    addProfilePlugin()
    addUtilPlugin()
//    addRecommendationsPlugin()
}
