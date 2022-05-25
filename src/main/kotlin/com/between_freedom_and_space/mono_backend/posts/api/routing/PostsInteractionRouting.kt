package com.between_freedom_and_space.mono_backend.posts.api.routing

import com.between_freedom_and_space.mono_backend.auth.components.plugin.extensions.getUserAuthorities
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.mappers.BasePostModelToPostModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.mappers.CreatePostRequestToCreateModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.mappers.UpdatePostRequestToUpdateModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.models.CreatePostRequest
import com.between_freedom_and_space.mono_backend.posts.api.models.PostModel
import com.between_freedom_and_space.mono_backend.posts.api.models.UpdatePostRequest
import com.between_freedom_and_space.mono_backend.posts.modules.qualifiers.PostMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.services.InteractionPostsService
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.InvalidPostException
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.CreatePostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.UpdatePostModel
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.postsInteractionRouting() {
    val basePath = "/post"

    val interactionService by inject<InteractionPostsService>()

    val postModelMapper by inject<ModelMapper<BasePostModel, PostModel>>(
        named(PostMappersQualifiers.BASE_POST_MODEL_TO_POST_MODEL)
    )
    val createPostMapper by inject<ModelMapper<CreatePostRequest, CreatePostModel>>(
        named(PostMappersQualifiers.CREATE_POST_REQUEST_TO_CREATE_MODEL)
    )
    val updatePostMapper by inject<ModelMapper<UpdatePostRequest, UpdatePostModel>>(
        named(PostMappersQualifiers.UPDATE_POST_REQUEST_TO_UPDATE_MODEL)
    )

    routing {

        patch("$basePath/create") {
            val authorities = getUserAuthorities()
            val userId = authorities.userId
            val createRequest = validateAndReceiveRequest<CreatePostRequest>()
            val createModel = createPostMapper.map(createRequest)

            val result = interactionService.createPost(userId, createModel)

            val postResponse = postModelMapper.map(result)
            val response = Response.ok(postResponse)

            sendResponse(response)
        }

        put("$basePath/{id}/update") {
            val authorities = getUserAuthorities()
            val userId = authorities.userId
            val updateRequest = validateAndReceiveRequest<UpdatePostRequest>()
            val updateModel = updatePostMapper.map(updateRequest)
            val postId = getPathParameter("id")?.toLong()
                ?: throw InvalidPostException("Post id is not presented")

            val result = interactionService.updatePost(postId, updateModel)

            val postResponse = postModelMapper.map(result)
            val response = Response.ok(postResponse)

            sendResponse(response)
        }

        delete("$basePath/{id}/delete") {
            val authorities = getUserAuthorities()
            val userId = authorities.userId
            val postId = getPathParameter("id")?.toLong()
                ?: throw InvalidPostException("Post id is not presented")

            val result = interactionService.deletePost(postId)

            val postResponse = postModelMapper.map(result)
            val response = Response.ok(postResponse)

            sendResponse(response)
        }
    }
}