package com.between_freedom_and_space.mono_backend.profiles.api.components

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForAdmins
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForEveryone
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForUsers
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.routingAccessor
import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.ADMIN
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.SUPER_ADMIN
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.AuthenticateException
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.InvalidPostException
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.InvalidProfileException
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import io.ktor.server.application.*
import io.ktor.server.routing.*

@Suppress("DuplicatedCode")
internal fun Application.profilesRoutingAccessors() {
    val basePath = "/profile"

    routing {

        grantAccessForEveryone("$basePath/exists")
        grantAccessForEveryone("$basePath/my")
        grantAccessForEveryone("$basePath/by-id/{id}")
        grantAccessForEveryone("$basePath/{nickname}")
        grantAccessForEveryone("$basePath/{nickname}/icon")
        grantAccessForEveryone("$basePath/{nickname}/subscriptions")
        grantAccessForEveryone("$basePath/{nickname}/subscribers")
        grantAccessForEveryone("$basePath/{nickname}/subscriptions/count")
        grantAccessForEveryone("$basePath/by-id/{id}/subscriptions/count")
        grantAccessForEveryone("$basePath/{nickname}/subscribers/count")
        grantAccessForEveryone("$basePath/by-id/{id}/subscribers/count")
        grantAccessForEveryone("$basePath/{nickname}/posts")
        grantAccessForEveryone("$basePath/by-id/{id}/posts")
        grantAccessForEveryone("$basePath/{nickname}/comments")
        grantAccessForEveryone("$basePath/{nickname}/tags")
        grantAccessForEveryone("$basePath/{nickname}/post/reactions")
        grantAccessForEveryone("$basePath/{nickname}/comment/reactions")

        grantAccessForUsers("$basePath/subscribe/{nickname}")
        grantAccessForUsers("$basePath/unsubscribe/{nickname}")
        grantAccessForUsers("$basePath/{nickname}/subscriptions/posts")

        grantAccessForAdmins("$basePath/all")
        grantAccessForAdmins("$basePath/create")

        routingAccessor("$basePath/{nickname}/set/icon", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userName = userAccessData.authority?.userName
                ?: throw AuthenticateException("User is not authenticated")
            val nickname = userAccessData.getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname is not presented")

            return@routingAccessor if (userName == nickname) {
                return@routingAccessor AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }

        routingAccessor("$basePath/{nickname}/update", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userName = userAccessData.authority?.userName
                ?: throw AuthenticateException("User is not authenticated")
            val nickname = userAccessData.getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname is not presented")

            return@routingAccessor if (userName == nickname) {
                AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }

        routingAccessor("$basePath/{nickname}/delete", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userName = userAccessData.authority?.userName
                ?: throw AuthenticateException("User is not authenticated")
            val nickname = userAccessData.getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname is not presented")

            return@routingAccessor if (userName == nickname) {
                AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }
    }
}