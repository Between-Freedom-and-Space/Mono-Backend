package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.routing.post

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.CreatePostReactionRequest
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.ReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.UpdatePostReactionRequest
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InteractionPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.exceptions.InvalidReactionException
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.CreatePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.UpdatePostReactionModel
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*

@Suppress("DuplicatedCode")
internal fun Application.postReactionsInteractionRouting() {
    val basePath = "/reaction/post"

    val interactionService by inject<InteractionPostReactionsService>()

    val baseMapper by inject<ModelMapper<BasePostReactionModel, ReactionModel>>()
    val createMapper by inject<ModelMapper<CreatePostReactionRequest, CreatePostReactionModel>>()
    val updateMapper by inject<ModelMapper<UpdatePostReactionRequest, UpdatePostReactionModel>>()

    routing {

        patch("$basePath/create") {
            val createRequest = validateAndReceiveRequest<CreatePostReactionRequest>()
            val createModel = createMapper.map(createRequest)

            val reaction = interactionService.create(createModel)

            val reactionResponse = baseMapper.map(reaction)
            val response = Response.ok(reactionResponse)

            sendResponse(response)
        }

        put("$basePath/{id}/update") {
            val updateRequest = validateAndReceiveRequest<UpdatePostReactionRequest>()
            val updateModel = updateMapper.map(updateRequest)
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidReactionException("Reaction id is not presented")

            val reaction = interactionService.update(id, updateModel)

            val reactionResponse = baseMapper.map(reaction)
            val response = Response.ok(reactionResponse)

            sendResponse(response)
        }

        delete("$basePath/{id}/delete") {
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidReactionException("Reaction id is not presented")

            val reaction = interactionService.delete(id)

            val reactionResponse = baseMapper.map(reaction)
            val response = Response.ok(reactionResponse)

            sendResponse(response)
        }
    }
}