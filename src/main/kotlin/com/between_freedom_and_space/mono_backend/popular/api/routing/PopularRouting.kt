package com.between_freedom_and_space.mono_backend.popular.api.routing

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.popular.api.models.GetPopularRequest
import com.between_freedom_and_space.mono_backend.popular.service.PopularService
import com.between_freedom_and_space.mono_backend.posts.api.models.PostModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.CommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.modules.qualifiers.CommentsMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.TagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.modules.qualifiers.TagsMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.modules.qualifiers.PostMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.profiles.api.models.ProfileModel
import com.between_freedom_and_space.mono_backend.profiles.modules.qualifiers.ProfilesMappersQualifiers
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

@Suppress("DuplicatedCode")
internal fun Application.popularRouting() {
    val basePath = "/popular"

    routing {

        val popularService by inject<PopularService>()

        val postsMapper by inject<ModelMapper<BasePostModel, PostModel>>(
            named(PostMappersQualifiers.BASE_POST_MODEL_TO_POST_MODEL)
        )
        val tagsMapper by inject<ModelMapper<BaseTagModel, TagModel>>(
            named(TagsMappersQualifiers.BASE_TAG_MODEL_TO_MODEL)
        )
        val profilesMapper by inject<ModelMapper<BaseProfileModel, ProfileModel>>(
            named(ProfilesMappersQualifiers.BASE_PROFILE_TO_PROFILE_MODEL)
        )
        val commentsMapper by inject<ModelMapper<BaseCommentModel, CommentModel>>(
            named(CommentsMappersQualifiers.BASE_COMMENT_TO_COMMENT_MODEL)
        )

        get("$basePath/posts") {
            val request = validateAndReceiveRequest<GetPopularRequest>()
            val pageNumber = request.pageParams.pageNumber
            val pageSize = request.pageParams.pageSize

            val posts = popularService.getPopularPosts(request.period, pageNumber, pageSize)

            val postsResponse = posts.map { postsMapper.map(it) }
            val response = Response.ok(postsResponse)

            sendResponse(response)
        }

        get("$basePath/tags") {
            val request = validateAndReceiveRequest<GetPopularRequest>()
            val pageNumber = request.pageParams.pageNumber
            val pageSize = request.pageParams.pageSize

            val tags = popularService.getPopularTags(request.period, pageNumber, pageSize)

            val tagsResponse = tags.map { tagsMapper.map(it) }
            val response = Response.ok(tagsResponse)

            sendResponse(response)
        }

        get("$basePath/comments") {
            val request = validateAndReceiveRequest<GetPopularRequest>()
            val pageNumber = request.pageParams.pageNumber
            val pageSize = request.pageParams.pageSize

            val comments = popularService.getPopularComments(request.period, pageNumber, pageSize)

            val commentsResponse = comments.map { commentsMapper.map(it) }
            val response = Response.ok(commentsResponse)

            sendResponse(response)
        }

        get("$basePath/profiles") {
            val request = validateAndReceiveRequest<GetPopularRequest>()
            val pageNumber = request.pageParams.pageNumber
            val pageSize = request.pageParams.pageSize

            val profiles = popularService.getPopularProfiles(request.period, pageNumber, pageSize)

            val profilesResponse = profiles.map { profilesMapper.map(it) }
            val response = Response.ok(profilesResponse)

            sendResponse(response)
        }
    }
}