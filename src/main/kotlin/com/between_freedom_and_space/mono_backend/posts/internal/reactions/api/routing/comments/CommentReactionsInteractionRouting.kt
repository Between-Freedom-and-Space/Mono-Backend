package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.routing.comments

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.CreateCommentReactionRequest
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.ReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.UpdateCommentReactionRequest
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.modules.qualifiers.ReactionsMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InteractionCommentReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.exceptions.InvalidReactionException
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreateCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.UpdateCommentReactionModel
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

@Suppress("DuplicatedCode")
internal fun Application.commentReactionsInteractionRouting() {
    val basePath = "/reaction/comment"

    val interactionService by inject<InteractionCommentReactionsService>()

    val baseMapper by inject<ModelMapper<BaseCommentReactionModel, ReactionModel>>(
        named(ReactionsMappersQualifiers.BASE_COMMENT_REACTION_TO_MODEL)
    )
    val createMapper by inject<ModelMapper<CreateCommentReactionRequest, CreateCommentReactionModel>>(
        named(ReactionsMappersQualifiers.CREATE_COMMENT_REACTION_REQUEST_TO_MODEL)
    )
    val updateMapper by inject<ModelMapper<UpdateCommentReactionRequest, UpdateCommentReactionModel>>(
        named(ReactionsMappersQualifiers.UPDATE_COMMENT_REACTION_REQUEST_TO_MODEL)
    )

    routing {

        patch("$basePath/create") {
            val createRequest = validateAndReceiveRequest<CreateCommentReactionRequest>()
            val createModel = createMapper.map(createRequest)

            val reaction = interactionService.createReaction(createModel)

            val reactionResponse = baseMapper.map(reaction)
            val response = Response.ok(reactionResponse)

            sendResponse(response)
        }

        put("$basePath/{id}/update") {
            val updateRequest = validateAndReceiveRequest<UpdateCommentReactionRequest>()
            val updateModel = updateMapper.map(updateRequest)
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidReactionException("Reaction id is not presented")

            val reaction = interactionService.updateReaction(id, updateModel)

            val reactionResponse = baseMapper.map(reaction)
            val response = Response.ok(reactionResponse)

            sendResponse(response)
        }

        delete("$basePath/{id}/delete") {
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidReactionException("Reaction id is not presented")

            val reaction = interactionService.deleteReaction(id)

            val reactionResponse = baseMapper.map(reaction)
            val response = Response.ok(reactionResponse)

            sendResponse(response)
        }
    }
}