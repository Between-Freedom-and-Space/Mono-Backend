package com.between_freedom_and_space.mono_backend.profiles.api.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.api.models.UpdateProfileRequest
import com.between_freedom_and_space.mono_backend.profiles.services.models.UpdateProfileModel

class UpdateProfileRequestToUpdateModelMapper: ModelMapper<UpdateProfileRequest, UpdateProfileModel> {

    override fun map(original: UpdateProfileRequest): UpdateProfileModel {
        return UpdateProfileModel(
            newNickName = original.newNickName,
            newNameAlias = original.newNameAlias,
            newPasswordEncrypted = original.newPasswordEncrypted,
            newDescription = original.newDescription,
            newMail = original.newMail,
            newPhoneNumber = original.newPhoneNumber,
            newLocation = original.newLocation
        )
    }
}