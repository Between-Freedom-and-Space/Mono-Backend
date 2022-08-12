package com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.api.models.ProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel

class BaseIconModelToModelMapper: ModelMapper<BaseProfileIconModel, ProfileIconModel> {

    override fun map(original: BaseProfileIconModel): ProfileIconModel {
        return ProfileIconModel(
            id = original.id,
            iconBase64 = original.iconBase64,
            profileId = original.profileId,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}