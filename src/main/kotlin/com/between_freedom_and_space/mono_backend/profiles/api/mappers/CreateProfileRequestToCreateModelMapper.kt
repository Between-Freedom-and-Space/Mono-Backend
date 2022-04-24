package com.between_freedom_and_space.mono_backend.profiles.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.api.models.CreateProfileRequest
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel

class CreateProfileRequestToCreateModelMapper: ModelMapper<CreateProfileRequest, CreateProfileModel> {

    override fun map(original: CreateProfileRequest): CreateProfileModel {
        return CreateProfileModel(
            mail = original.mail,
            phoneNumber = original.phoneNumber,
            passwordEncrypted = original.passwordEncrypted,
            nickName = original.nickName,
            nameAlias = original.nameAlias,
            description = original.description,
            location = original.description,
        )
    }
}