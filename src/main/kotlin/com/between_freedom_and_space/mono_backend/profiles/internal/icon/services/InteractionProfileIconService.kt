package com.between_freedom_and_space.mono_backend.profiles.internal.icon.services

import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.CreateProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.UpdateProfileIconModel

interface InteractionProfileIconService {

    fun createProfileIcon(createModel: CreateProfileIconModel): BaseProfileIconModel

    fun updateProfileIcon(iconId: Long, updateModel: UpdateProfileIconModel): BaseProfileIconModel

    fun deleteProfileIcon(iconId: Long)
}