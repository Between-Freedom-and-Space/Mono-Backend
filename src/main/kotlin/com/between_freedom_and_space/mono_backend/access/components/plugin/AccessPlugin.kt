package com.between_freedom_and_space.mono_backend.access.components.plugin

import com.between_freedom_and_space.mono_backend.access.components.PathPatternMatcher
import com.between_freedom_and_space.mono_backend.access.components.exceptions.AccessException
import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.models.UserAccessData
import com.between_freedom_and_space.mono_backend.access.components.plugin.service.RoutingAccessor
import com.between_freedom_and_space.mono_backend.access.components.plugin.util.roleAttributeKey
import com.between_freedom_and_space.mono_backend.auth.components.plugin.extensions.getUserAuthoritiesOrNull
import com.between_freedom_and_space.mono_backend.auth.components.plugin.util.userAuthorityAttributeKey
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import mu.KotlinLogging

class AccessPlugin(
    config: Configuration,
    private val handler: PluginAccessHandler,
    private val pathMatcher: PathPatternMatcher,
) {
    companion object: Plugin<ApplicationCallPipeline, Configuration, AccessPlugin> {

        override val key = AttributeKey<AccessPlugin>("ApplicationAccessPlugin")

        override fun install(pipeline: ApplicationCallPipeline, configure: Configuration.() -> Unit): AccessPlugin {
            val handler by inject<PluginAccessHandler>()
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
        var defaultRoutingAccessor: RoutingAccessor? = null,
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
        val path = request.path()
        val authority = context.getUserAuthoritiesOrNull()

        handler.handleRequest(request, attributes)
        log("Request with path: $path handled for user: $authority")

        if (allowAllRequests) {
            return
        }

        val routingAccessor = findPathAccessor(path)
        routingAccessor?.let { accessor ->
            val userAccessData = buildUserAccessData(request, attributes)
            val accessCheckResult = accessor(userAccessData)
            log("Path accessor invoked for user: $authority with result: $accessCheckResult")

            if (accessCheckResult == AccessVerifyResult.ACCESSED) {
                return
            }
        }

        defaultRoutingAccessor?.let { accessor ->
            val userAccessData = buildUserAccessData(request, attributes)
            val accessCheckResult = accessor(userAccessData)
            log("Default path accessor invoked for user: $authority with result: $accessCheckResult")

            if (accessCheckResult == AccessVerifyResult.ACCESSED) {
                return
            }
        }

        val userRoleCheckResult = handler.checkRoleAccess(authority, request)
        if (userRoleCheckResult == AccessVerifyResult.ACCESSED) {
            log("Accessed User pole for user: $authority")
            return
        }

        val userRuleToRoleCheckResult = handler.checkRuleToRoleAccess(authority, request)
        if (userRuleToRoleCheckResult == AccessVerifyResult.ACCESSED) {
            log("Accessed User role rule for user: $authority")
            return
        }

        val userRuleToUserCheckResult = handler.checkRuleToUserAccess(authority, request)
        if (userRuleToUserCheckResult == AccessVerifyResult.ACCESSED) {
            log("Accessed User personal path rule for user: $authority")
            return
        }

        log("Access rejected for user: $authority")
        throw AccessException("Access denied: no enough rights")
    }

    private fun log(message: String) {
        if (enableLogging) {
            logger.info("Access Plugin: $message")
        }
    }

    private fun findPathAccessor(path: String): RoutingAccessor? {
        val allAccessors = routingAccessors.toList()

        allAccessors.forEach {
            val pathPattern = it.first
            val accessor = it.second

            if (pathMatcher.pathMatchesPattern(pathPattern, path)) {
                return accessor
            }
        }

        return null
    }

    private fun buildUserAccessData(request: ApplicationRequest, attributes: Attributes): UserAccessData {
        val authority = attributes.getOrNull(userAuthorityAttributeKey)
        val role = attributes[roleAttributeKey]
        return UserAccessData(authority, request, role)
    }
}