package com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models.UpdateProfileIconRequest
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.UpdateProfileIconModel

class UpdateProfileIconRequestToModelMapper: ModelMapper<UpdateProfileIconRequest, UpdateProfileIconModel> {

    override fun map(original: UpdateProfileIconRequest): UpdateProfileIconModel {
        return UpdateProfileIconModel(
            newIconBase64 = original.newIconBase64,
        )
    }
}