package com.between_freedom_and_space.mono_backend.access.components.plugin.extensions

import com.between_freedom_and_space.mono_backend.access.components.exceptions.AccessException
import com.between_freedom_and_space.mono_backend.access.components.plugin.AccessPlugin
import com.between_freedom_and_space.mono_backend.access.components.plugin.service.RoutingAccessor
import com.between_freedom_and_space.mono_backend.access.components.plugin.util.roleAttributeKey
import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

fun PipelineContext<Unit, ApplicationCall>.getUserRole(): Role {
    return getUserRoleOrNull()
        ?: throw AccessException("User role not found")
}

fun PipelineContext<Unit, ApplicationCall>.getUserRoleOrNull(): Role? {
    val attributes = call.attributes
    return attributes.getOrNull(roleAttributeKey)
}

@ContextDsl
fun Routing.routingAccessor(path: String, accessor: RoutingAccessor): AccessPlugin {
    val plugin = application.pluginOrNull(AccessPlugin)
    if (plugin != null) {
        plugin.setRoutingAccessor(path, accessor)
        return plugin
    }

    return application.install(AccessPlugin) {
        routingAccessors = mapOf(path to accessor)
    }
}