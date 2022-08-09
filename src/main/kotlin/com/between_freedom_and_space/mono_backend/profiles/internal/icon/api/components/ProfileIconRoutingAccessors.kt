package com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.components

import com.between_freedom_and_space.mono_backend.access.components.exceptions.AccessException
import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForAdmins
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.routingAccessor
import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.ADMIN
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.SUPER_ADMIN
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.AuthenticateException
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.InformationProfileIconService
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.exceptions.InvalidProfileIconException
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import io.ktor.server.routing.*

@Suppress("DuplicatedCode")
internal fun Application.profileIconRoutingAccessors() {
    val basePath = "/profile/icon"

    routing {

        grantAccessForAdmins("$basePath/all")
        grantAccessForAdmins("$basePath/{id}")
    }

    routing {

        grantAccessForAdmins("$basePath/create")

        val service by inject<InformationProfileIconService>()

        routingAccessor("$basePath/{id}/update", ADMIN, SUPER_ADMIN) { accessData ->
            val userId = accessData.authority?.userId
                ?: throw AuthenticateException("User is not authenticated")
            val iconId = accessData.getPathParameter("id")?.toLong()
                ?: throw InvalidProfileIconException("Profile icon id is not presented")

            val iconUser = service.getProfileIconUser(iconId)

            return@routingAccessor if (iconUser.userId == userId) {
                return@routingAccessor AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }

        routingAccessor("$basePath/{id}/delete", ADMIN, SUPER_ADMIN) { accessData ->
            val userId = accessData.authority?.userId
                ?: throw AuthenticateException("User is not authenticated")
            val iconId = accessData.getPathParameter("id")?.toLong()
                ?: throw InvalidProfileIconException("Profile icon id is not presented")

            val iconUser = service.getProfileIconUser(iconId)

            return@routingAccessor if (iconUser.userId == userId) {
                return@routingAccessor AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }
    }
}