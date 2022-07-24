package com.between_freedom_and_space.mono_backend.posts.internal.comments.api.components

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForAdmins
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForEveryone
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForUsers
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.routingAccessor
import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.ADMIN
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.SUPER_ADMIN
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InformationCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.exceptions.InvalidCommentException
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import io.ktor.server.routing.*

@Suppress("DuplicatedCode")
internal fun Application.commentsRoutingAccessors() {
    val basePath = "/comment"

    routing {

        grantAccessForEveryone("$basePath/{id}")

        grantAccessForUsers("$basePath/create")

        grantAccessForAdmins("$basePath/all")

        val commentInformationService by inject<InformationCommentsService>()

        routingAccessor("$basePath/{id}/update", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userId = userAccessData.authority?.userId
            val commentId = userAccessData.getPathParameter("id")?.toLong()
                ?: throw InvalidCommentException("Comment id is not provided")
            val commentAuthor = commentInformationService.getCommentAuthorId(commentId)

            return@routingAccessor if (userId == commentAuthor.id) {
                AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }

        routingAccessor("$basePath/{id}/delete", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userId = userAccessData.authority?.userId
            val commentId = userAccessData.getPathParameter("id")?.toLong()
                ?: throw InvalidCommentException("Comment id is not provided")
            val commentAuthor = commentInformationService.getCommentAuthorId(commentId)

            return@routingAccessor if (userId == commentAuthor.id) {
                AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }
    }
}