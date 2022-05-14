package com.between_freedom_and_space.mono_backend.profiles.services.impl

import com.between_freedom_and_space.mono_backend.profiles.services.InteractionProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.UpdateProfileModel

class InteractionProfileServiceImpl: InteractionProfilesService {

    override fun createProfile(createModel: CreateProfileModel): BaseProfileModel {
        TODO("Not yet implemented")
    }

    override fun updateProfile(targetProfileId: Long, updateModel: UpdateProfileModel): BaseProfileModel {
        TODO("Not yet implemented")
    }

    override fun updateProfile(targetProfileNickName: String, updateModel: UpdateProfileModel): BaseProfileModel {
        TODO("Not yet implemented")
    }

    override fun deleteProfile(targetProfileId: Long): BaseProfileModel {
        TODO("Not yet implemented")
    }

    override fun deleteProfile(targetProfileNickName: String): BaseProfileModel {
        TODO("Not yet implemented")
    }
}