package com.between_freedom_and_space.mono_backend.access.components.plugin

import com.between_freedom_and_space.mono_backend.access.components.PathPatternMatcher
import com.between_freedom_and_space.mono_backend.access.components.exceptions.AccessException
import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.models.PathMatchResult
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
    companion object : Plugin<ApplicationCallPipeline, Configuration, AccessPlugin> {

        override val key = AttributeKey<AccessPlugin>("ApplicationAccessPlugin")

        private val logger = KotlinLogging.logger { }

        override fun install(pipeline: ApplicationCallPipeline, configure: Configuration.() -> Unit): AccessPlugin {
            val handler by inject<PluginAccessHandler>()
            val pathMatcher by inject<PathPatternMatcher>()

            val config = Configuration().apply(configure)
            val plugin = AccessPlugin(config, handler, pathMatcher)

            config.routingAccessors.forEach { entry ->
                logger.info { "Registered routing accessor for path: ${entry.key}" }
            }

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

    private val enableLogging = config.enableLogging

    private val routingAccessors = config.routingAccessors.toMutableMap()

    private val allowAllRequests = config.allowAllRequests

    private val defaultRoutingAccessor = config.defaultRoutingAccessor

    fun setRoutingAccessor(pathPattern: String, accessor: RoutingAccessor) {
        routingAccessors[pathPattern] = accessor
        logger.info { "Registered routing accessor for path: $pathPattern" }
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

        val (routingAccessor, matchResult) = findPathAccessor(path)
        val pathParams = matchResult?.pathTokens ?: emptyMap()
        val userAccessData = buildUserAccessData(request, attributes, pathParams)
        routingAccessor?.let { accessor ->
            val accessCheckResult = accessor(userAccessData)
            log("Path accessor invoked for user: $authority with result: $accessCheckResult")

            if (accessCheckResult == AccessVerifyResult.ACCESSED) {
                return
            }
        } ?: defaultRoutingAccessor?.let { accessor ->
            val accessCheckResult = accessor(userAccessData)
            log("Default path accessor invoked for user: $authority with result: $accessCheckResult")

            if (accessCheckResult == AccessVerifyResult.ACCESSED) {
                return
            }
        }

        val userRoleCheckResult = handler.checkRoleAccess(userAccessData)
        if (userRoleCheckResult == AccessVerifyResult.ACCESSED) {
            log("Accessed User pole for user: $authority")
            return
        }

        val userRuleToRoleCheckResult = handler.checkRuleToRoleAccess(userAccessData)
        if (userRuleToRoleCheckResult == AccessVerifyResult.ACCESSED) {
            log("Accessed User role rule for user: $authority")
            return
        }

        val userRuleToUserCheckResult = handler.checkRuleToUserAccess(userAccessData)
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

    private fun findPathAccessor(path: String): Pair<RoutingAccessor?, PathMatchResult?> {
        val allAccessors = routingAccessors.toList()
        var currentAccessor: RoutingAccessor? = null
        var currentMatchResult: PathMatchResult? = null
        var minStrength = Int.MAX_VALUE

        allAccessors.forEach {
            val pathPattern = it.first
            val accessor = it.second

            val matchResult = pathMatcher.pathMatchesPattern(pathPattern, path)
            if (!matchResult.match) {
                return@forEach
            }
            if (matchResult.strength < minStrength) {
                minStrength = matchResult.strength
                currentAccessor = accessor
                currentMatchResult = matchResult
            }
        }

        return currentAccessor!! to currentMatchResult!!
    }

    private fun buildUserAccessData(
        request: ApplicationRequest, attributes: Attributes, pathVariables: Map<String, String>
    ): UserAccessData {
        val authority = attributes.getOrNull(userAuthorityAttributeKey)
        val role = attributes[roleAttributeKey]
        return UserAccessData(authority, request, pathVariables, role)
    }
}