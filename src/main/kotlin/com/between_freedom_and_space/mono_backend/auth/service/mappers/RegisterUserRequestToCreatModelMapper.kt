package com.between_freedom_and_space.mono_backend.auth.service.mappers

import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserRequest
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel

class RegisterUserRequestToCreatModelMapper: ModelMapper<RegisterUserRequest, CreateProfileModel> {

    override fun map(original: RegisterUserRequest): CreateProfileModel {
        return CreateProfileModel(
            mail = original.mail,
            passwordEncrypted = original.passwordEncrypted,
            nickName = original.nickName,
            nameAlias = original.nameAlias,
            description = original.description,
            location = original.location,
        )
    }
}