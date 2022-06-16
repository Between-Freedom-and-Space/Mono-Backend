package com.between_freedom_and_space.mono_backend.app

import com.between_freedom_and_space.mono_backend.access.plugin.addAccessPlugin
import com.between_freedom_and_space.mono_backend.app.config.configure
import com.between_freedom_and_space.mono_backend.app.config.configureDatabase
import com.between_freedom_and_space.mono_backend.auth.components.plugin.AuthenticatePlugin
import com.between_freedom_and_space.mono_backend.auth.components.plugin.config.IgnoredPath
import com.between_freedom_and_space.mono_backend.auth.plugins.addAuthPlugin
import com.between_freedom_and_space.mono_backend.common.components.BaseExceptionHandler
import com.between_freedom_and_space.mono_backend.common.plugins.ExceptionHandlerPlugin
import com.between_freedom_and_space.mono_backend.posts.plugins.addPostsPlugin
import com.between_freedom_and_space.mono_backend.profiles.plugins.addProfilePlugin
import com.between_freedom_and_space.mono_backend.recommendations.plugin.addRecommendationsPlugin
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.plugins.*
import io.ktor.server.routing.*
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.slf4j.event.Level

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

    // Components
    addAccessPlugin()
    addAuthPlugin()
    addPostsPlugin()
    addProfilePlugin()
//    addRecommendationsPlugin()
}
