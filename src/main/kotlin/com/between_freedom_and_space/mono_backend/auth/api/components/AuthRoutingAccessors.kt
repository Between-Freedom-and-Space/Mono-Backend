package com.between_freedom_and_space.mono_backend.auth.api.components

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForEveryone
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.routingAccessor
import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.ADMIN
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.SUPER_ADMIN
import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.authRoutingAccessors() {

    routing {
        val basePath = "/auth/token"

        grantAccessForEveryone("$basePath/verifyAccess")
        grantAccessForEveryone("$basePath/verifyRefresh")
        grantAccessForEveryone("$basePath/refreshAccess")
    }

    routing {
        val basePath = "/auth/user"

        grantAccessForEveryone("$basePath/register")
        grantAccessForEveryone("$basePath/authenticate")
        grantAccessForEveryone("$basePath/delete")
    }
}