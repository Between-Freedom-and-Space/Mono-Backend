package com.between_freedom_and_space.mono_backend.profiles.services.impl

import com.between_freedom_and_space.mono_backend.auth.components.UserPasswordEncryptor
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.services.InteractionProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.ProfileNotFoundException
import com.between_freedom_and_space.mono_backend.profiles.services.mappers.UserProfileToBaseProfileModelMapper
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.UpdateProfileModel
import org.jetbrains.exposed.sql.transactions.transaction

class InteractionProfileServiceImpl(
    private val profileRepository: CommonProfilesRepository,
    private val entityMapper: ModelMapper<UserProfile, BaseProfileModel>,
): InteractionProfilesService {

    override fun createProfile(createModel: CreateProfileModel): BaseProfileModel {
        val entity = transaction {
            profileRepository.createProfile(createModel)
        }
        return entityMapper.map(entity)
    }

    override fun updateProfile(targetProfileId: Long, updateModel: UpdateProfileModel): BaseProfileModel {
        val entity = transaction {
            val profile = profileRepository.getProfileById(targetProfileId)
                ?: throw ProfileNotFoundException("Profile with id: $targetProfileId not found")

            updateProfile(profile, updateModel)
            profileRepository.saveProfile(profile)
        }

        return entityMapper.map(entity)
    }

    override fun updateProfile(targetProfileNickName: String, updateModel: UpdateProfileModel): BaseProfileModel {
        val entity = transaction {
            val profile = profileRepository.getProfileByNickname(targetProfileNickName)
                ?: throw ProfileNotFoundException("Profile with nickname: $targetProfileNickName not found")

            updateProfile(profile, updateModel)
            profileRepository.saveProfile(profile)
        }

        return entityMapper.map(entity)
    }

    override fun deleteProfile(targetProfileId: Long): BaseProfileModel {
        val entity = transaction {
            profileRepository.deleteProfileById(targetProfileId)
                ?: throw ProfileNotFoundException("Profile with id: $targetProfileId not found")
        }
        return entityMapper.map(entity)
    }

    override fun deleteProfile(targetProfileNickName: String): BaseProfileModel {
        val entity = transaction {
            profileRepository.deleteProfileByNickname(targetProfileNickName)
                ?: throw ProfileNotFoundException("Profile with nickname: $targetProfileNickName not found")
        }
        return entityMapper.map(entity)
    }

    private fun updateProfile(profile: UserProfile, updateModel: UpdateProfileModel) {
        updateModel.newDescription?.let { profile.description = it }
        updateModel.newLocation?.let { profile.locataion = it }
        updateModel.newMail?.let { profile.mail = it }
        updateModel.newNameAlias?.let { profile.nameAlias = it }
        updateModel.newNickName?.let { profile.nickName = it }
        updateModel.newPhoneNumber?.let { profile.phoneNumber = it }
        updateModel.newPasswordEncrypted?.let { profile.passwordEncrypted = it }
    }
}