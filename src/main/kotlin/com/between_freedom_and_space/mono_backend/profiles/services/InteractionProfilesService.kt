package com.between_freedom_and_space.mono_backend.profiles.services

import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.UpdateProfileModel

interface InteractionProfilesService {

    fun createProfile(createModel: CreateProfileModel): BaseProfileModel

    fun updateProfile(targetProfileId: Long, updateModel: UpdateProfileModel): BaseProfileModel

    fun updateProfile(targetProfileNickName: String, updateModel: UpdateProfileModel): BaseProfileModel

    fun deleteProfile(targetProfileId: Long): BaseProfileModel

    fun deleteProfile(targetProfileNickName: String): BaseProfileModel
}