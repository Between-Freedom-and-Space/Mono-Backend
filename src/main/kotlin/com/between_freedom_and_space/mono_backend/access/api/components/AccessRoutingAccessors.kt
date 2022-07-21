package com.between_freedom_and_space.mono_backend.access.api.components

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForAdmins
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForSuperAdmins
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.routingAccessor
import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.accessRoutingAccessors() {

    routing {
        val basePath = "/access/role"

        grantAccessForAdmins("$basePath/all")
        grantAccessForAdmins("$basePath/{id}")
        grantAccessForAdmins("$basePath/user/{nickname}")
    }

    routing {
        val basePath = "/access/rule"

        grantAccessForAdmins("$basePath/all")
        grantAccessForAdmins("$basePath/{id}")
        grantAccessForAdmins("$basePath/user/{nickname}")
        grantAccessForAdmins("$basePath/role/{roleAlias}")
        grantAccessForAdmins("$basePath/user/{nickname}/has/access")
        grantAccessForAdmins("$basePath/role/{alias}/has/access")

        grantAccessForSuperAdmins("$basePath/create")
        grantAccessForSuperAdmins("$basePath/user/create")
        grantAccessForSuperAdmins("$basePath/role/create")
        grantAccessForSuperAdmins("$basePath/{id}/update")
        grantAccessForSuperAdmins("$basePath/user/{id}/update")
        grantAccessForSuperAdmins("$basePath/role/{id}/update")
        grantAccessForSuperAdmins("$basePath/{id}/delete")
        grantAccessForSuperAdmins("$basePath/user/{id}/delete")
        grantAccessForSuperAdmins("$basePath/role/{id}/delete")
    }
}