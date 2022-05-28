package com.between_freedom_and_space.mono_backend.posts.internal.comments.api.routing

import com.between_freedom_and_space.mono_backend.auth.components.plugin.extensions.getUserAuthorities
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.CommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.CreateCommentRequest
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.UpdateCommentRequest
import com.between_freedom_and_space.mono_backend.posts.internal.comments.modules.qualifiers.CommentsMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InteractionCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.exceptions.InvalidCommentException
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.CreateCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.UpdateCommentModel
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.commentsInteractionRouting() {
    val basePath = "/comment"

    val interactionService by inject<InteractionCommentsService>()

    val createMapper by inject<ModelMapper<CreateCommentRequest, CreateCommentModel>>(
        named(CommentsMappersQualifiers.CREATE_COMMENT_REQUEST_TO_CREATE_MODEL)
    )
    val updateMapper by inject<ModelMapper<UpdateCommentRequest, UpdateCommentModel>>(
        named(CommentsMappersQualifiers.UPDATE_COMMENT_REQUEST_TO_UPDATE_MODEL)
    )
    val baseMapper by inject<ModelMapper<BaseCommentModel, CommentModel>>(
        named(CommentsMappersQualifiers.BASE_COMMENT_TO_COMMENT_MODEL)
    )

    routing {

        patch("$basePath/create") {
            val authority = getUserAuthorities()
            val authorId = authority.userId
            val createRequest = validateAndReceiveRequest<CreateCommentRequest>()
            val postId = createRequest.postId
            val createModel = createMapper.map(createRequest)

            val comment = interactionService.createComment(authorId, postId, createModel)

            val commentResponse = baseMapper.map(comment)
            val response = Response.ok(commentResponse)

            sendResponse(response)
        }

        put("$basePath/{id}/update") {
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidCommentException("Comment id is not presented")
            val updateRequest = validateAndReceiveRequest<UpdateCommentRequest>()
            val updateModel = updateMapper.map(updateRequest)

            val comment = interactionService.updateComment(id, updateModel)

            val commentResponse = baseMapper.map(comment)
            val response = Response.ok(commentResponse)

            sendResponse(response)
        }

        delete("$basePath/{id}/delete") {
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidCommentException("Comment id is not presented")

            val comment = interactionService.deleteComment(id)

            val commentResponse = baseMapper.map(comment)
            val response = Response.ok(commentResponse)

            sendResponse(response)
        }
    }
}