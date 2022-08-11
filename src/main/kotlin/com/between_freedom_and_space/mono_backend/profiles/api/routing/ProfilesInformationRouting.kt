package com.between_freedom_and_space.mono_backend.profiles.api.routing

import com.between_freedom_and_space.mono_backend.common.api.PageParams
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.models.PostModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.CommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.modules.qualifiers.CommentsMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.ReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.modules.qualifiers.ReactionsMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.TagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.modules.qualifiers.TagsMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.modules.qualifiers.PostMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.profiles.api.models.ProfileModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models.ProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.modules.qualifiers.ProfilesMappersQualifiers
import com.between_freedom_and_space.mono_backend.profiles.services.InformationProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.InvalidProfileException
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

@Suppress("DuplicatedCode")
internal fun Application.profilesInformationRouting() {
    val basePath = "/profile"

    val informationService by inject<InformationProfilesService>()
    val profileMapper by inject<ModelMapper<BaseProfileModel, ProfileModel>>(
        named(ProfilesMappersQualifiers.BASE_PROFILE_TO_PROFILE_MODEL)
    )

    routing {

        get("$basePath/all") {
            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageSize = pageParams.pageSize
            val pageNumber = pageParams.pageNumber

            val result = informationService.getAllProfiles(pageNumber, pageSize)

            val profileResponse = result.map { profileMapper.map(it) }
            val response = Response.ok(profileResponse)

            sendResponse(response)
        }

        get("$basePath/{nickname}") {
            val nickName = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = informationService.getProfileByNickName(nickName)

            val profileResponse = profileMapper.map(result)
            val response = Response.ok(profileResponse)

            sendResponse(response)
        }

        get("$basePath/{nickname}/icon") {
            val iconMapper by inject<ModelMapper<BaseProfileIconModel, ProfileIconModel>>(
                named(ProfilesMappersQualifiers.BASE_PROFILE_TO_PROFILE_MODEL)
            )

            val nickname = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = informationService.getProfileIconOrNull(nickname)

            val iconResponse = result?.let { iconMapper.map(it) }
            val response = Response.ok(iconResponse)

            sendResponse(response)
        }

        get("$basePath/{nickname}/subscriptions") {
            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageSize = pageParams.pageSize
            val pageNumber = pageParams.pageNumber
            val nickName = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = informationService.getProfileSubscriptions(nickName, pageNumber, pageSize)

            val profileResponse = result.map { profileMapper.map(it) }
            val response = Response.ok(profileResponse)

            sendResponse(response)
        }

        get("$basePath/{nickname}/subscribers") {
            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageSize = pageParams.pageSize
            val pageNumber = pageParams.pageNumber
            val nickName = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = informationService.getProfileSubscribers(nickName, pageNumber, pageSize)

            val profileResponse = result.map { profileMapper.map(it) }
            val response = Response.ok(profileResponse)

            sendResponse(response)
        }

        get("$basePath/{nickname}/subscriptions/count") {
            val nickName = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = informationService.getProfileSubscriptionsCount(nickName)

            val response = Response.ok(result)

            sendResponse(response)
        }

        get("$basePath/{nickname}/subscribers/count") {
            val nickName = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = informationService.getProfileSubscribersCount(nickName)

            val response = Response.ok(result)

            sendResponse(response)
        }


        get("$basePath/{nickname}/posts") {
            val postsMapper by inject<ModelMapper<BasePostModel, PostModel>>(
                named(PostMappersQualifiers.BASE_POST_MODEL_TO_POST_MODEL)
            )

            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageSize = pageParams.pageSize
            val pageNumber = pageParams.pageNumber
            val nickName = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = informationService.getProfilePosts(nickName, pageNumber, pageSize)

            val postsResponse = result.map { postsMapper.map(it) }
            val response = Response.ok(postsResponse)

            sendResponse(response)
        }

        get("$basePath/{nickname}/comments") {
            val commentsMapper by inject<ModelMapper<BaseCommentModel, CommentModel>>(
                named(CommentsMappersQualifiers.BASE_COMMENT_TO_COMMENT_MODEL)
            )

            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageSize = pageParams.pageSize
            val pageNumber = pageParams.pageNumber
            val nickName = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = informationService.getProfileComments(nickName, pageNumber, pageSize)

            val commentsResponse = result.map { commentsMapper.map(it) }
            val response = Response.ok(commentsResponse)

            sendResponse(response)
        }

        get("$basePath/{nickname}/tags") {
            val tagsMapper by inject<ModelMapper<BaseTagModel, TagModel>>(
                named(TagsMappersQualifiers.BASE_TAG_MODEL_TO_MODEL)
            )

            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageSize = pageParams.pageSize
            val pageNumber = pageParams.pageNumber
            val nickName = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = informationService.getProfileTags(nickName, pageNumber, pageSize)

            val tagsResponse = result.map { tagsMapper.map(it) }
            val response = Response.ok(tagsResponse)

            sendResponse(response)
        }

        get("$basePath/{nickname}/post/reactions") {
            val reactionsMapper by inject<ModelMapper<BasePostReactionModel, ReactionModel>>(
                named(ReactionsMappersQualifiers.BASE_POST_REACTION_TO_MODEL)
            )

            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageSize = pageParams.pageSize
            val pageNumber = pageParams.pageNumber
            val nickName = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = informationService.getProfilePostReactions(nickName, pageNumber, pageSize)

            val reactionsResponse = result.map { reactionsMapper.map(it) }
            val response = Response.ok(reactionsResponse)

            sendResponse(response)
        }

        get("$basePath/{nickname}/comment/reactions") {
            val reactionsMapper by inject<ModelMapper<BaseCommentReactionModel, ReactionModel>>(
                named(ReactionsMappersQualifiers.BASE_COMMENT_REACTION_TO_MODEL)
            )

            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageSize = pageParams.pageSize
            val pageNumber = pageParams.pageNumber
            val nickName = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = informationService.getProfileCommentReactions(nickName, pageNumber, pageSize)

            val reactionsResponse = result.map { reactionsMapper.map(it) }
            val response = Response.ok(reactionsResponse)

            sendResponse(response)
        }

        get("$basePath/{nickname}/subscriptions/posts") {
            val postsMapper by inject<ModelMapper<BasePostModel, PostModel>>(
                named(PostMappersQualifiers.BASE_POST_MODEL_TO_POST_MODEL)
            )

            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageSize = pageParams.pageSize
            val pageNumber = pageParams.pageNumber
            val nickname = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = informationService.getProfileSubscriptionsPosts(nickname, pageNumber, pageSize)

            val postsResponse = result.map { postsMapper.map(it) }
            val response = Response.ok(postsResponse)

            sendResponse(response)
        }
    }
}