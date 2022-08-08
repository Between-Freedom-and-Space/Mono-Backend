package com.between_freedom_and_space.mono_backend.app.config

import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForEveryone
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import io.ktor.server.application.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.routing.*
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry

internal fun MicrometerMetrics.Configuration.configure(application: Application) {
    val appMicrometerRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT)

    registry = appMicrometerRegistry

    application.addPrometheusRouting(appMicrometerRegistry)
}

private fun Application.addPrometheusRouting(registry: PrometheusMeterRegistry) {

    routing {

        get("/metrics") {
            sendResponse(registry.scrape())
        }

        grantAccessForEveryone("/metrics")
    }
}