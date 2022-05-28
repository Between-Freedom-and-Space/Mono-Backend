package com.between_freedom_and_space.mono_backend.auth.service.impl

import com.between_freedom_and_space.mono_backend.auth.service.UserProfileAuthService
import com.between_freedom_and_space.mono_backend.auth.service.model.UserAuthModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.services.mappers.UserProfileToBaseProfileModelMapper
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.util.extensions.transform
import org.jetbrains.exposed.sql.transactions.transaction

class CommonUserProfileAuthService(
    private val profileRepository: CommonProfilesRepository,
    private val profileEntityMapper: ModelMapper<UserProfile, UserAuthModel>
): UserProfileAuthService {

    override fun getProfileOrNull(id: Long): UserAuthModel? {
        val entity = transaction {
            profileRepository.getProfileById(id)
        }

        return entity?.let { profileEntityMapper.map(it) }
    }

    override fun getProfileOrNull(nickName: String): UserAuthModel? {
        val entity = transaction {
            profileRepository.getProfileByNickname(nickName)
        }
        return entity?.let { profileEntityMapper.map(it) }
    }
}