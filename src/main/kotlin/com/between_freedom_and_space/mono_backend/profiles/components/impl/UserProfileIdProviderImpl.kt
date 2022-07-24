package com.between_freedom_and_space.mono_backend.profiles.components.impl

import com.between_freedom_and_space.mono_backend.profiles.components.UserProfileIdProvider
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.ProfileNotFoundException
import org.jetbrains.exposed.dao.id.EntityID

class UserProfileIdProviderImpl(
    private val profileRepository: CommonProfilesRepository
): UserProfileIdProvider {

    override fun checkUserExists(nickname: String): Boolean {
        return profileRepository.profileExistsWith(nickname)
    }

    override fun checkUserExists(userId: Long): Boolean {
        return profileRepository.profileExistsWith(userId)
    }

    override fun getUser(nickname: String): EntityID<Long> {
        val user = profileRepository.getProfileByNickname(nickname)
            ?: throw ProfileNotFoundException("Profile with nickname: $nickname not found")
        return user.id
    }

    override fun getUser(userId: Long): EntityID<Long> {
        val user = profileRepository.getProfileById(userId)
            ?: throw ProfileNotFoundException("Profile with id: $userId not found")
        return user.id
    }
}