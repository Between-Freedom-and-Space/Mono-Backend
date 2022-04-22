package com.between_freedom_and_space.mono_backend.profiles.services.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.models.UserProfileModel

class UserProfileToUserProfileModelMapper: ModelMapper<UserProfile, UserProfileModel> {

    override fun map(original: UserProfile): UserProfileModel {
        return UserProfileModel(
            id = original.id.value,
            mail = original.mail,
            passwordEncrypted = original.passwordEncrypted,
            nickName = original.nickName,
            nameAlias = original.nameAlias,
            description = original.description,
            location = original.locataion,
            isVisible = original.isVisible,
            isDeleted = original.isDeleted,
        )
    }
}