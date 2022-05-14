package com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.routing.post

import com.between_freedom_and_space.mono_backend.common.api.PageParams
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.ReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.exceptions.InvalidReactionException
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*

@Suppress("DuplicatedCode")
internal fun Application.postReactionsInformationRouting() {
    val basePath = "/reaction/post"

    val informationService by inject<InformationPostReactionsService>()

    val baseMapper by inject<ModelMapper<BasePostReactionModel, ReactionModel>>()

    routing {

        get("$basePath/all") {
            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageNumber = pageParams.pageNumber
            val pageSize = pageParams.pageSize

            val reactions = informationService.getAllReactions(pageNumber, pageSize)

            val reactionsResponse = reactions.map { baseMapper.map(it) }
            val response = Response.ok(reactionsResponse)

            sendResponse(response)
        }

        get("$basePath/{id}") {
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidReactionException("Reaction id is not presented")

            val reaction = informationService.getReactionById(id)

            val reactionResponse = baseMapper.map(reaction)
            val response = Response.ok(reactionResponse)

            sendResponse(response)
        }
    }
}