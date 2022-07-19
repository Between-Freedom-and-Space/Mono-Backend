package com.between_freedom_and_space.mono_backend.profiles.api.components

import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForAdmins
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForEveryone
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForUsers
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.routingAccessor
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

internal fun Application.profilesRoutingAccessors() {
    val basePath = "/profile"

    routing {

        grantAccessForEveryone("$basePath/exists")
        grantAccessForEveryone("$basePath/{nickname}")
        grantAccessForEveryone("$basePath/{nickname}/subscriptions")
        grantAccessForEveryone("$basePath/{nickname}/subscribers")
        grantAccessForEveryone("$basePath/{nickname}/subscriptions/count")
        grantAccessForEveryone("$basePath/{nickname}/subscribers/count")
        grantAccessForEveryone("$basePath/{nickname}/posts")
        grantAccessForEveryone("$basePath/{nickname}/comments")
        grantAccessForEveryone("$basePath/{nickname}/tags")
        grantAccessForEveryone("$basePath/{nickname}/post/reactions")
        grantAccessForEveryone("$basePath/{nickname}/comment/reactions")

        grantAccessForUsers("$basePath/subscribe/{nickname}")
        grantAccessForUsers("$basePath/unsubscribe/{nickname}")

        grantAccessForAdmins("$basePath/all")
        grantAccessForAdmins("$basePath/create")

        routingAccessor("$basePath/{nickname}/update") { userAccessData ->

        }
    }
}