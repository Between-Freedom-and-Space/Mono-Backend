package com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.repository.model.CreateProfileIconEntityModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.CreateProfileIconModel

class CreateProfileIconModelToCreateEntityMapper: ModelMapper<CreateProfileIconModel, CreateProfileIconEntityModel> {

    override fun map(original: CreateProfileIconModel): CreateProfileIconEntityModel {
        return CreateProfileIconEntityModel(
            iconBase64 = original.iconBase64,
        )
    }
}