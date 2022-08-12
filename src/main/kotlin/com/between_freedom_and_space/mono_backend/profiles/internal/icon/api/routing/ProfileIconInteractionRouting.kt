package com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.routing

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models.CreateProfileIconRequest
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models.ProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models.UpdateProfileIconRequest
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.modules.qualifiers.ProfileIconMappersQualifiers
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.InteractionProfileIconService
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.exceptions.InvalidProfileIconException
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.CreateProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.UpdateProfileIconModel
import com.between_freedom_and_space.mono_backend.util.extensions.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.profileIconInteractionRouting() {
    val basePath = "/profile/icon"

    routing {

        val service by inject<InteractionProfileIconService>()

        val baseMapper by inject<ModelMapper<BaseProfileIconModel, ProfileIconModel>>(
            named(ProfileIconMappersQualifiers.BASE_PROFILE_ICON_MODEL_TO_MODEL_MAPPER)
        )
        val createMapper by inject<ModelMapper<CreateProfileIconRequest, CreateProfileIconModel>>(
            named(ProfileIconMappersQualifiers.CREATE_PROFILE_ICON_REQUEST_TO_MODEL_MAPPER)
        )
        val updateMapper by inject<ModelMapper<UpdateProfileIconRequest, UpdateProfileIconModel>>(
            named(ProfileIconMappersQualifiers.UPDATE_PROFILE_ICON_REQUEST_TO_MODEL_MAPPER)
        )

        patch("$basePath/create") {
            val request = validateAndReceiveRequest<CreateProfileIconRequest>()
            val createModel = createMapper.map(request)

            val createdIcon = service.createProfileIcon(createModel)

            val iconResponse = baseMapper.map(createdIcon)
            val response = Response.ok(iconResponse)

            sendResponse(response)
        }

        put("$basePath/{id}/update") {
            val request = validateAndReceiveRequest<UpdateProfileIconRequest>()
            val updateModel = updateMapper.map(request)
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidProfileIconException("Profile icon id is not presented")

            val updatedIcon = service.updateProfileIcon(id, updateModel)

            val iconResponse = baseMapper.map(updatedIcon)
            val response = Response.ok(iconResponse)

            sendResponse(response)
        }

        delete("$basePath/{id}/delete") {
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidProfileIconException("Profile icon id is not presented")

            service.deleteProfileIcon(id)

            sendEmptyResponse()
        }
    }
}