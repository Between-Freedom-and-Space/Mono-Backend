package com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models.CreateProfileIconRequest
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.CreateProfileIconModel

class CreateProfileIconRequestToModelMapper: ModelMapper<CreateProfileIconRequest, CreateProfileIconModel> {

    override fun map(original: CreateProfileIconRequest): CreateProfileIconModel {
        return CreateProfileIconModel(
            iconBase64 = original.iconBase64,
            profileId = original.profileId,
        )
    }
}