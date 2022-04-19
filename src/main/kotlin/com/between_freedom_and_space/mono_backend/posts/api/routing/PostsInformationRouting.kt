package com.between_freedom_and_space.mono_backend.posts.api.routing

import com.between_freedom_and_space.mono_backend.common.api.PageParams
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.postsInformationRouting() {
    val basePath = "/post"

    routing {

        get("$basePath/all") {
            val pageParams = validateAndReceiveRequest<PageParams>()

        }

        get("$basePath/{id}") {

        }

        get("$basePath/{id}/comments") {
            val pageParams = validateAndReceiveRequest<PageParams>()

        }

        get("$basePath/{id}/reactions") {
            val pageParams = validateAndReceiveRequest<PageParams>()
        }

        get("$basePath/{id}/reactions/count") {

        }

        get("$basePath/{id}/tags") {
            val pageParams = validateAndReceiveRequest<PageParams>()

        }
    }
}