package com.between_freedom_and_space.mono_backend.auth.api.mappers

import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserResponse
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.models.UserProfileModel

class UserModelToRegisterResponseMapper: ModelMapper<UserProfileModel, RegisterUserResponse> {

    override fun map(original: UserProfileModel): RegisterUserResponse {
        return RegisterUserResponse(
            nickName = original.nickName,
            nameAlias = original.nameAlias,
            description = original.description,
            location = original.location,
        )
    }
}