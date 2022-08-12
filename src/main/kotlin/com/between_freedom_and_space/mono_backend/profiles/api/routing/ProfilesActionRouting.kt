package com.between_freedom_and_space.mono_backend.profiles.api.routing

import com.between_freedom_and_space.mono_backend.auth.components.plugin.extensions.getUserAuthorities
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.api.models.ProfileExistsRequest
import com.between_freedom_and_space.mono_backend.profiles.api.models.ProfileModel
import com.between_freedom_and_space.mono_backend.profiles.api.models.SetProfileIconRequest
import com.between_freedom_and_space.mono_backend.profiles.api.models.SubscribeActionResponse
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models.ProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.modules.qualifiers.ProfileIconMappersQualifiers
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.modules.qualifiers.ProfilesMappersQualifiers
import com.between_freedom_and_space.mono_backend.profiles.services.ActionProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.InvalidProfileException
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.SubscribeActionResult
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.profilesActionRouting() {
    val basePath = "/profile"

    val actionService by inject<ActionProfilesService>()

    val iconBaseMapper by inject<ModelMapper<BaseProfileIconModel, ProfileIconModel>>(
        named(ProfileIconMappersQualifiers.BASE_PROFILE_ICON_MODEL_TO_MODEL_MAPPER)
    )
    val subscribeActionMapper by inject<ModelMapper<SubscribeActionResult, SubscribeActionResponse>>(
        named(ProfilesMappersQualifiers.SUBSCRIBE_RESULT_TO_SUBSCRIBE_RESPONSE)
    )

    routing {

        post("$basePath/exists") {
            val request = validateAndReceiveRequest<ProfileExistsRequest>()
            val nickName = request.nickName

            val result = actionService.profileExists(nickName)

            val response = Response.ok(result)

            sendResponse(response)
        }

        post("$basePath/subscribe/{nickname}") {
            val authorities = getUserAuthorities()
            val authorId = authorities.userId
            val subscribeNickName = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = actionService.subscribeTo(authorId, subscribeNickName)

            val subscribeResponse = subscribeActionMapper.map(result)
            val response = Response.ok(subscribeResponse)

            sendResponse(response)
        }

        post("$basePath/unsubscribe/{nickname}") {
            val authorities = getUserAuthorities()
            val authorId = authorities.userId
            val unsubscribeNickname = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = actionService.unsubscribeFrom(authorId, unsubscribeNickname)

            val unsubscribeResponse = subscribeActionMapper.map(result)
            val response = Response.ok(unsubscribeResponse)

            sendResponse(response)
        }

        post("$basePath/{nickname}/set/icon") {
            val request = validateAndReceiveRequest<SetProfileIconRequest>()
            val nickname = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname is not presented")

            val profile = actionService.setProfileIcon(nickname, request.iconBase64)

            val profileResponse = iconBaseMapper.map(profile)
            val response = Response.ok(profileResponse)

            sendResponse(response)
        }
    }
}