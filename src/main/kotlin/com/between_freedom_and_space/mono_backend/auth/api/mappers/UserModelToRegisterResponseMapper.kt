package com.between_freedom_and_space.mono_backend.auth.api.mappers

import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserResponse
import com.between_freedom_and_space.mono_backend.auth.service.model.UserAuthModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class UserModelToRegisterResponseMapper: ModelMapper<UserAuthModel, RegisterUserResponse> {

    override fun map(original: UserAuthModel): RegisterUserResponse {
        return RegisterUserResponse(
            id = original.id,
            nickName = original.nickName,
        )
    }
}