package com.between_freedom_and_space.mono_backend.auth.api.mappers

import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserResponse
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel

class UserModelToRegisterResponseMapper: ModelMapper<BaseProfileModel, RegisterUserResponse> {

    override fun map(original: BaseProfileModel): RegisterUserResponse {
        return RegisterUserResponse(
            id = original.id,
            nickName = original.nickName,
        )
    }
}