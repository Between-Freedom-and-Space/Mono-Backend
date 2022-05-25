package com.between_freedom_and_space.mono_backend.posts.api.routing

import com.between_freedom_and_space.mono_backend.auth.components.plugin.extensions.getUserAuthorities
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.models.CommentPostRequest
import com.between_freedom_and_space.mono_backend.posts.api.models.PostModel
import com.between_freedom_and_space.mono_backend.posts.api.models.ReactPostRequest
import com.between_freedom_and_space.mono_backend.posts.modules.qualifiers.PostMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.services.ActionPostsService
import com.between_freedom_and_space.mono_backend.posts.services.InteractionPostsService
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.InvalidPostException
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.postsActionRouting() {
    val basePath = "/post"

    val actionService by inject<ActionPostsService>()

    val postMapper by inject<ModelMapper<BasePostModel, PostModel>>(
        named(PostMappersQualifiers.BASE_POST_MODEL_TO_POST_MODEL)
    )

    routing {

        post("$basePath/{id}/react") {
            val authorities = getUserAuthorities()
            val reactRequest = validateAndReceiveRequest<ReactPostRequest>()
            val reaction = reactRequest.reactAlias
            val userId = authorities.userId
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidPostException("Post id is not presented")

            val result = actionService.reactPost(reaction, id, userId)

            val postResponse = postMapper.map(result)
            val response = Response.ok(postResponse)

            sendResponse(response)
        }

        post("$basePath/{id}/comment") {
            val authorities = getUserAuthorities()
            val commentRequest = validateAndReceiveRequest<CommentPostRequest>()
            val text = commentRequest.commentText
            val userId = authorities.userId
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidPostException("Post id is not presented")

            val result = actionService.commentPost(text, id, userId)

            val postResponse = postMapper.map(result)
            val response = Response.ok(postResponse)

            sendResponse(response)
        }
    }
}