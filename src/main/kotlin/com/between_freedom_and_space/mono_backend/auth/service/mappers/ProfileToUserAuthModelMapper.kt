package com.between_freedom_and_space.mono_backend.auth.service.mappers

import com.between_freedom_and_space.mono_backend.auth.service.model.UserAuthModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile

class ProfileToUserAuthModelMapper: ModelMapper<UserProfile, UserAuthModel> {

    override fun map(original: UserProfile): UserAuthModel {
        return UserAuthModel(
            id = original.id.value,
            nickName = original.nickName,
            passwordEncrypted = original.passwordEncrypted,
        )
    }
}