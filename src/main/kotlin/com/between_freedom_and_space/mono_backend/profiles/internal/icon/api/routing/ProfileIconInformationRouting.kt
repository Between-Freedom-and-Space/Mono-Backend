package com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.routing

import com.between_freedom_and_space.mono_backend.common.api.PageParams
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models.ProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.modules.qualifiers.ProfileIconMappersQualifiers
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.InformationProfileIconService
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.exceptions.InvalidProfileIconException
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.profileIconInformationRouting() {
    val basePath = "/profile/icon"

    routing {

        val service by inject<InformationProfileIconService>()
        val baseMapper by inject<ModelMapper<BaseProfileIconModel, ProfileIconModel>>(
            named(ProfileIconMappersQualifiers.BASE_PROFILE_ICON_MODEL_TO_MODEL_MAPPER)
        )

        get("$basePath/all") {
            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageNumber = pageParams.pageNumber
            val pageSize = pageParams.pageSize

            val icons = service.getAllProfileIcons(pageNumber, pageSize)

            val iconsResponse = icons.map { baseMapper.map(it) }
            val response = Response.ok(iconsResponse)

            sendResponse(response)
        }

        get("$basePath/{id}") {
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidProfileIconException("Profile icon id is not presented")

            val icon = service.getProfileIconById(id)

            val iconResponse = baseMapper.map(icon)
            val response = Response.ok(iconResponse)

            sendResponse(response)
        }
    }
}