package com.between_freedom_and_space.mono_backend.profiles.services.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel

class UserProfileToBaseProfileModelMapper: ModelMapper<UserProfile, BaseProfileModel> {

    override fun map(original: UserProfile): BaseProfileModel {
        return BaseProfileModel(
            id = original.id.value,
            nickName = original.nickName,
            nameAlias = original.nameAlias,
            description = original.description,
            location = original.location,
            isVisible = original.isVisible,
            isDeleted = original.isDeleted,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}