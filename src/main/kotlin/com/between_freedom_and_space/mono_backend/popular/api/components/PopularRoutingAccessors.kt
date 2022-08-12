package com.between_freedom_and_space.mono_backend.popular.api.components

import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForEveryone
import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.popularRoutingAccessors() {

    routing {
        val basePath = "/popular/last/created"

        grantAccessForEveryone("$basePath/posts")
        grantAccessForEveryone("$basePath/tags")
        grantAccessForEveryone("$basePath/profiles")
        grantAccessForEveryone("$basePath/comments")
    }
}