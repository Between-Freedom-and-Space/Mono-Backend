package com.between_freedom_and_space.mono_backend.profiles.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.api.models.ProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel

class BaseProfileModelToProfileModelMapper: ModelMapper<BaseProfileModel, ProfileModel> {

    override fun map(original: BaseProfileModel): ProfileModel {
        return ProfileModel(
            id = original.id,
            nickName = original.nickName,
            nameAlias = original.nameAlias,
            description = original.description,
            location = original.location,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}