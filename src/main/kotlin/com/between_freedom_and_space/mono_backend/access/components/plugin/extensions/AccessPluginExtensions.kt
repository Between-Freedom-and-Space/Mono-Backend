package com.between_freedom_and_space.mono_backend.access.components.plugin.extensions

import com.between_freedom_and_space.mono_backend.access.components.exceptions.AccessException
import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.AccessPlugin
import com.between_freedom_and_space.mono_backend.access.components.plugin.models.UserAccessData
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

@ContextDsl
fun Routing.routingAccessor(
    path: String,
    grantedRoles: List<Role> = listOf(),
    accessor: RoutingAccessor
): AccessPlugin {
    val decoratedAccessor = fun (userAccessData: UserAccessData): AccessVerifyResult {
        val role = userAccessData.role

        return if (role in grantedRoles) {
            AccessVerifyResult.ACCESSED
        } else {
            accessor(userAccessData)
        }
    }

    return routingAccessor(path, accessor)
}

@ContextDsl
fun Routing.routingAccessor(
    path: String,
    vararg grantedRoles: Role,
    accessor: RoutingAccessor
): AccessPlugin {
    return routingAccessor(path, grantedRoles.toList(), accessor)
}

@ContextDsl
fun Routing.grantAccessForEveryone(path: String): AccessPlugin {
    val accessor = fun (_: UserAccessData): AccessVerifyResult {
        return AccessVerifyResult.ACCESSED
    }
    return routingAccessor(path, accessor)
}

@ContextDsl
fun Routing.grantAccessForUsers(path: String): AccessPlugin {
    val accessor = fun (accessData: UserAccessData): AccessVerifyResult {
        return if (accessData.role != Role.NO_ROLE) {
            AccessVerifyResult.ACCESSED
        } else {
            AccessVerifyResult.REJECTED
        }
    }
    return routingAccessor(path, accessor)
}

@ContextDsl
fun Routing.grantAccessForAdmins(path: String): AccessPlugin {
    val accessor = fun (accessData: UserAccessData): AccessVerifyResult {
        return when(accessData.role) {
            Role.ADMIN -> AccessVerifyResult.ACCESSED
            Role.SUPER_ADMIN -> AccessVerifyResult.ACCESSED
            else -> AccessVerifyResult.REJECTED
        }
    }
    return routingAccessor(path, accessor)
}

@ContextDsl
fun Routing.grantAccessForSuperAdmins(path: String): AccessPlugin {
    val accessor = fun (accessData: UserAccessData): AccessVerifyResult {
        return if (accessData.role == Role.SUPER_ADMIN) {
            AccessVerifyResult.ACCESSED
        } else {
            AccessVerifyResult.REJECTED
        }
    }
    return routingAccessor(path, accessor)
}