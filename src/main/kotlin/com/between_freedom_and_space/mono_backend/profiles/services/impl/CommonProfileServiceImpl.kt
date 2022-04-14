package com.between_freedom_and_space.mono_backend.profiles.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.entities.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.models.UserProfileModel
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.services.CommonProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.CreationProfileException
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.ProfileNotFoundException
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel
import org.jetbrains.exposed.sql.transactions.transaction

class CommonProfileServiceImpl(
    private val profileMapper: ModelMapper<UserProfile, UserProfileModel>,
    private val repository: CommonProfilesRepository,
): CommonProfilesService {

    override fun getProfile(userId: Long): UserProfileModel {
        val profile = transaction { repository.getProfileById(userId) }
            ?: throw ProfileNotFoundException("Profile with userId: $userId not found")
        return profileMapper.map(profile)
    }

    override fun getProfile(nickname: String): UserProfileModel {
        val profile = transaction { repository.getProfileByNickname(nickname) }
            ?: throw ProfileNotFoundException("Profile with nickname: $nickname not found")
        return profileMapper.map(profile)
    }

    override fun getProfileOrNull(userId: Long): UserProfileModel? {
        val profile = transaction { repository.getProfileById(userId) }
        return if (profile != null) {
            profileMapper.map(profile)
        } else { null }
    }

    override fun getProfileOrNull(nickname: String): UserProfileModel? {
        val profile = transaction { repository.getProfileByNickname(nickname) }
        return if (profile != null) {
            profileMapper.map(profile)
        } else { null }
    }

    override fun createProfile(createModel: CreateProfileModel): UserProfileModel {
        val createdProfile = transaction {
            val profileExists = repository.profileExistsWith(createModel.nickName)
            if (profileExists) {
                throw CreationProfileException("Profile with nickname: ${createModel.nickName} exists")
            }

            return@transaction repository.saveProfile(createModel)
        }
        return profileMapper.map(createdProfile)
    }

    override fun changeVisibility(userId: Long, isVisible: Boolean): UserProfileModel {
        return changeVisibilityOrNull(userId, isVisible)
            ?: throw ProfileNotFoundException("Profile with id: $userId not found")
    }

    override fun changeVisibility(nickname: String, isVisible: Boolean): UserProfileModel {
        return changeVisibilityOrNull(nickname, isVisible)
            ?: throw ProfileNotFoundException("Profile with nickname: $nickname not found")
    }

    override fun changeVisibilityOrNull(userId: Long, isVisible: Boolean): UserProfileModel? {
        val updatedProfile = transaction {
            val profile = repository.getProfileById(userId)
            profile?.isVisible = isVisible
            profile?.let { repository.updateProfile(it) }
        }

        return if (updatedProfile != null) {
            profileMapper.map(updatedProfile)
        } else { null }
    }

    override fun changeVisibilityOrNull(nickname: String, isVisible: Boolean): UserProfileModel? {
        val updatedProfile = transaction {
            val profile = repository.getProfileByNickname(nickname)
            profile?.isVisible = isVisible
            profile?.let { repository.updateProfile(it) }
        }

        return if (updatedProfile != null) {
            profileMapper.map(updatedProfile)
        } else { null }
    }

    override fun markAsDeleted(userProfileId: Long): UserProfileModel {
        return markAsDeletedOrNull(userProfileId)
            ?: throw ProfileNotFoundException("Profile with id: $userProfileId not found")
    }

    override fun markAsDeleted(nickname: String): UserProfileModel {
        return markAsDeletedOrNull(nickname)
            ?: throw ProfileNotFoundException("Profile with nickname: $nickname not found")
    }

    override fun markAsDeletedOrNull(userProfileId: Long): UserProfileModel? {
        val updatedProfile = transaction {
            val profile = repository.getProfileById(userProfileId)
            profile?.isDeleted = true
            profile?.let { repository.updateProfile(it) }
        }

        return if (updatedProfile != null) {
            profileMapper.map(updatedProfile)
        } else { null }
    }

    override fun markAsDeletedOrNull(nickname: String): UserProfileModel? {
        val updatedProfile = transaction {
            val profile = repository.getProfileByNickname(nickname)
            profile?.isDeleted = true
            profile?.let { repository.updateProfile(it) }
        }

        return if (updatedProfile != null) {
            profileMapper.map(updatedProfile)
        } else { null }
    }
}