package com.between_freedom_and_space.mono_backend.posts.api.routing

import com.between_freedom_and_space.mono_backend.common.api.PageParams
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.models.PostModel
import com.between_freedom_and_space.mono_backend.posts.api.models.PostReactionsCountResponse
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.CommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.ReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.TagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.modules.qualifiers.PostMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.services.InformationPostsService
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.InvalidPostException
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.PostReactionsCountModel
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.postsInformationRouting() {
    val basePath = "/post"

    val informationService by inject<InformationPostsService>()
    val baseModelMapper by inject<ModelMapper<BasePostModel, PostModel>>(
        named(PostMappersQualifiers.BASE_POST_MODEL_TO_POST_MODEL)
    )

    routing {

        get("$basePath/all") {
            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageNumber = pageParams.pageNumber
            val pageSize = pageParams.pageSize

            val posts = informationService.getAllPosts(pageNumber, pageSize)

            val postsResponse = posts.map { baseModelMapper.map(it) }
            val response = Response.ok(postsResponse)

            sendResponse(response)
        }

        get("$basePath/{id}") {
            val postId = getPathParameter("id")?.toLong()
                ?: throw InvalidPostException("Post id is not presented")

            val post = informationService.getPostById(postId)

            val postResponse = baseModelMapper.map(post)
            val response = Response.ok(postResponse)

            sendResponse(response)
        }

        get("$basePath/{id}/comments") {
            val commentMapper by inject<ModelMapper<BaseCommentModel, CommentModel>>()

            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageNumber = pageParams.pageNumber
            val pageSize = pageParams.pageSize
            val postId = getPathParameter("id")?.toLong()
                ?: throw InvalidPostException("Post id is not presented")

            val comments = informationService.getPostComments(postId, pageNumber, pageSize)

            val commentsResponse = comments.map { commentMapper.map(it) }
            val response = Response.ok(commentsResponse)

            sendResponse(response)
        }

        get("$basePath/{id}/reactions") {
            val reactionMapper by inject<ModelMapper<BasePostReactionModel, ReactionModel>>()

            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageNumber = pageParams.pageNumber
            val pageSize = pageParams.pageSize
            val postId = getPathParameter("id")?.toLong()
                ?: throw InvalidPostException("Post id is not presented")

            val reactions = informationService.getPostReactions(postId, pageNumber, pageSize)

            val reactionsResponse = reactions.map { reactionMapper.map(it) }
            val response = Response.ok(reactionsResponse)

            sendResponse(response)
        }

        get("$basePath/{id}/reactions/count") {
            val countMapper by inject<ModelMapper<PostReactionsCountModel, PostReactionsCountResponse>>(
                named(PostMappersQualifiers.POST_REACTIONS_COUNT_MODEL_TO_POST_REACTIONS_COUNT_RESPONSE)
            )

            val postId = getPathParameter("id")?.toLong()
                ?: throw InvalidPostException("Post id is not presented")

            val countModel = informationService.getPostReactionsCount(postId)

            val countResponse = countMapper.map(countModel)
            val response = Response.ok(countResponse)

            sendResponse(response)
        }

        get("$basePath/{id}/tags") {
            val tagMapper by inject<ModelMapper<BaseTagModel, TagModel>>()

            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageNumber = pageParams.pageNumber
            val pageSize = pageParams.pageSize
            val postId = getPathParameter("id")?.toLong()
                ?: throw InvalidPostException("Post id is not presented")

            val tags = informationService.getPostTags(postId, pageNumber, pageSize)

            val tagResponse = tags.map { tagMapper.map(it) }
            val response = Response.ok(tagResponse)

            sendResponse(response)
        }
    }
}