package com.between_freedom_and_space.mono_backend.access.components.plugin

import io.ktor.server.application.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import mu.KotlinLogging

class AccessPlugin(
    config: Configuration
) {
    companion object: Plugin<ApplicationCallPipeline, Configuration, AccessPlugin> {

        override val key = AttributeKey<AccessPlugin>("ApplicationAccessPlugin")

        override fun install(pipeline: ApplicationCallPipeline, configure: Configuration.() -> Unit): AccessPlugin {
            TODO("Not yet implemented")
        }
    }

    data class Configuration(
        var enableLogging: Boolean = true,
    )

    private val logger = KotlinLogging.logger {  }

    private suspend fun intercept(context: PipelineContext<Unit, ApplicationCall>) {

    }
}