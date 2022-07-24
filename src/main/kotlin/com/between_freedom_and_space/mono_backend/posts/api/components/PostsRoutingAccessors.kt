package com.between_freedom_and_space.mono_backend.posts.api.components

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForAdmins
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForEveryone
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForUsers
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.routingAccessor
import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.ADMIN
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.SUPER_ADMIN
import com.between_freedom_and_space.mono_backend.posts.services.InformationPostsService
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.InvalidPostException
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import io.ktor.server.routing.*

@Suppress("DuplicatedCode")
internal fun Application.postsRoutingAccessors() {
    val basePath = "/post"

    routing {

        grantAccessForEveryone("$basePath/{id}")
        grantAccessForEveryone("$basePath/{id}/comments")
        grantAccessForEveryone("$basePath/{id}/reactions")
        grantAccessForEveryone("$basePath/{id}/comments/count")
        grantAccessForEveryone("$basePath/{id}/reactions/count")
        grantAccessForEveryone("$basePath/{id}/tags")

        grantAccessForUsers("$basePath/{id}/react")
        grantAccessForUsers("$basePath/{id}/comment")
        grantAccessForUsers("$basePath/create")

        grantAccessForAdmins("$basePath/all")

        val postInformationService by inject<InformationPostsService>()

        routingAccessor("$basePath/{id}/update", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userId = userAccessData.authority?.userId
            val postId = userAccessData.getPathParameter("id")?.toLong()
                ?: throw InvalidPostException("Post id is not presented")
            val postAuthorId = postInformationService.getPostAuthorId(postId)

            return@routingAccessor if (userId == postAuthorId.id) {
                 AccessVerifyResult.ACCESSED
            } else {
                 AccessVerifyResult.REJECTED
            }
        }

        routingAccessor("$basePath/{id}/delete", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userId = userAccessData.authority?.userId
            val postId = userAccessData.getPathParameter("id")?.toLong()
                ?: throw InvalidPostException("Post id is not presented")
            val postAuthorId = postInformationService.getPostAuthorId(postId)

            return@routingAccessor if (userId == postAuthorId.id) {
                AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }
    }
}