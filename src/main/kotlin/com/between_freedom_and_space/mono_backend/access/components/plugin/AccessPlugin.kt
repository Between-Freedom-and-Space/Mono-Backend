package com.between_freedom_and_space.mono_backend.access.components.plugin

import com.between_freedom_and_space.mono_backend.access.components.PathPatternMatcher
import com.between_freedom_and_space.mono_backend.access.components.plugin.service.RoutingAccessor
import com.between_freedom_and_space.mono_backend.access.components.plugin.service.impl.DefaultRoutingAccessor
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import mu.KotlinLogging

class AccessPlugin(
    config: Configuration,
    private val handler: AccessHandler,
    private val pathMatcher: PathPatternMatcher,
) {
    companion object: Plugin<ApplicationCallPipeline, Configuration, AccessPlugin> {

        override val key = AttributeKey<AccessPlugin>("ApplicationAccessPlugin")

        override fun install(pipeline: ApplicationCallPipeline, configure: Configuration.() -> Unit): AccessPlugin {
            val handler by inject<AccessHandler>()
            val pathMatcher by inject<PathPatternMatcher>()

            val config = Configuration().apply(configure)
            val plugin = AccessPlugin(config, handler, pathMatcher)

            pipeline.intercept(ApplicationCallPipeline.Plugins) {
                plugin.intercept(this)
            }

            return plugin
        }
    }

    data class Configuration(
        var enableLogging: Boolean = true,
        var routingAccessors: Map<String, RoutingAccessor> = mapOf(),
        var allowAllRequests: Boolean = false,
        var defaultRoutingAccessor: RoutingAccessor = DefaultRoutingAccessor(),
    )

    private val logger = KotlinLogging.logger {  }

    private val enableLogging = config.enableLogging

    private val routingAccessors = config.routingAccessors.toMutableMap()

    private val allowAllRequests = config.allowAllRequests

    private val defaultRoutingAccessor = config.defaultRoutingAccessor

    fun setRoutingAccessor(pathPattern: String, accessor: RoutingAccessor) {
        routingAccessors[pathPattern] = accessor
    }

    private fun intercept(context: PipelineContext<Unit, ApplicationCall>) {
        val request = context.call.request
        val attributes = context.call.attributes

        try {
            handler.intercept(request, attributes)
        } catch (ex: Exception) {

        }
    }
}