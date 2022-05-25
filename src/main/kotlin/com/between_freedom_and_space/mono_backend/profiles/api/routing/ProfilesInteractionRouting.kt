package com.between_freedom_and_space.mono_backend.profiles.api.routing

import com.between_freedom_and_space.mono_backend.auth.components.plugin.extensions.getUserAuthorities
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.api.models.CreateProfileRequest
import com.between_freedom_and_space.mono_backend.profiles.api.models.ProfileModel
import com.between_freedom_and_space.mono_backend.profiles.api.models.UpdateProfileRequest
import com.between_freedom_and_space.mono_backend.profiles.modules.ProfilesMappersQualifiers
import com.between_freedom_and_space.mono_backend.profiles.services.InteractionProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.InvalidProfileException
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.UpdateProfileModel
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.profilesInteractionRouting() {
    val basePath = "/profile"

    val interactionService by inject<InteractionProfilesService>()
    val profileMapper by inject<ModelMapper<BaseProfileModel, ProfileModel>>(
        named(ProfilesMappersQualifiers.BASE_PROFILE_TO_PROFILE_MODEL)
    )

    routing {

        patch("$basePath/create") {
            val createProfileMapper by inject<ModelMapper<CreateProfileRequest, CreateProfileModel>>(
                named(ProfilesMappersQualifiers.CREATE_PROFILE_REQUEST_TO_CREATE_PROFILE_MODEL)
            )

            val request = validateAndReceiveRequest<CreateProfileRequest>()
            val createModel = createProfileMapper.map(request)

            val result = interactionService.createProfile(createModel)

            val profileResponse = profileMapper.map(result)
            val response = Response.ok(profileResponse)

            sendResponse(response)
        }

        put("$basePath/{nickname}/update") {
            val updateProfileMapper by inject<ModelMapper<UpdateProfileRequest, UpdateProfileModel>>(
                named(ProfilesMappersQualifiers.UPDATE_PROFILE_REQUEST_TO_UPDATE_PROFILE_MODEL)
            )

            val request = validateAndReceiveRequest<UpdateProfileRequest>()
            val updateModel = updateProfileMapper.map(request)
            val nickName = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = interactionService.updateProfile(nickName, updateModel)

            val profileResponse = profileMapper.map(result)
            val response = Response.ok(profileResponse)

            sendResponse(response)
        }

        delete("$basePath/{nickname}/delete") {
            val nickName = getPathParameter("nickname")
                ?: throw InvalidProfileException("Nickname value is not presented")

            val result = interactionService.deleteProfile(nickName)

            val profileResponse = profileMapper.map(result)
            val response = Response.ok(profileResponse)

            sendResponse(response)
        }
    }
}