package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.components

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForAdmins
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForEveryone
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForUsers
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.routingAccessor
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.ADMIN
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.SUPER_ADMIN
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationCommentReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.exceptions.InvalidReactionException
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import io.ktor.server.routing.*

@Suppress("DuplicatedCode")
internal fun Application.reactionsRoutingAccessors() {

    routing {
        val basePath = "/reaction/comment"

        grantAccessForEveryone("$basePath/{id}")

        grantAccessForUsers("$basePath/create")

        grantAccessForAdmins("$basePath/all")

        val reactionInformationService by inject<InformationCommentReactionsService>()

        routingAccessor("$basePath/{id}/update", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userId = userAccessData.authority?.userId
            val reactionId = userAccessData.request.getPathParameter("id")?.toLong()
                ?: throw InvalidReactionException("Comment reaction id is not presented")
            val authorId = reactionInformationService.getReactionAuthorId(reactionId)

            return@routingAccessor if (userId == authorId.id) {
                AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }

        routingAccessor("$basePath/{id}/delete", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userId = userAccessData.authority?.userId
            val reactionId = userAccessData.request.getPathParameter("id")?.toLong()
                ?: throw InvalidReactionException("Comment reaction id is not presented")
            val authorId = reactionInformationService.getReactionAuthorId(reactionId)

            return@routingAccessor if (userId == authorId.id) {
                AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }
    }

    routing {
        val basePath = "/reaction/post"

        grantAccessForEveryone("$basePath/{id}")

        grantAccessForUsers("$basePath/create")

        grantAccessForAdmins("$basePath/all")

        val reactionInformationService by inject<InformationPostReactionsService>()

        routingAccessor("$basePath/{id}/update", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userId = userAccessData.authority?.userId
            val reactionId = userAccessData.request.getPathParameter("id")?.toLong()
                ?: throw InvalidReactionException("Comment reaction id is not presented")
            val authorId = reactionInformationService.getReactionAuthorId(reactionId)

            return@routingAccessor if (userId == authorId.id) {
                AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }

        routingAccessor("$basePath/{id}/delete", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userId = userAccessData.authority?.userId
            val reactionId = userAccessData.request.getPathParameter("id")?.toLong()
                ?: throw InvalidReactionException("Comment reaction id is not presented")
            val authorId = reactionInformationService.getReactionAuthorId(reactionId)

            return@routingAccessor if (userId == authorId.id) {
                AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }
    }
}