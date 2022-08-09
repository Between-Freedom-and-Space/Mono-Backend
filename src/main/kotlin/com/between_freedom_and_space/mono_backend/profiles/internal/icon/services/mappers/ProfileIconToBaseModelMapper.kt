package com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.entities.ProfileIcon
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel

class ProfileIconToBaseModelMapper: ModelMapper<ProfileIcon, BaseProfileIconModel> {

    override fun map(original: ProfileIcon): BaseProfileIconModel {
        return BaseProfileIconModel(
            id = original.id.value,
            iconBase64 = original.iconBase64,
            profileId = original.profile.value,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}