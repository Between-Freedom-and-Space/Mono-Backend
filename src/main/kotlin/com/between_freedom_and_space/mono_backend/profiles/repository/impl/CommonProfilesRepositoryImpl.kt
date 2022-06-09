package com.between_freedom_and_space.mono_backend.profiles.repository.impl

import com.between_freedom_and_space.mono_backend.common.exposed.extensions.exists
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserProfilesTable
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.repository.exceptions.ProfileAlreadyDeletedException
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*

class CommonProfilesRepositoryImpl: CommonProfilesRepository {

    override fun getAllProfiles(pageNumber: Int, pageSize: Int): List<UserProfile> {
        val offset = (pageNumber - 1).toLong() * pageSize.toLong()
        val query = UserProfilesTable
            .selectAll()
            .limit(pageSize, offset)
        val result = UserProfile.wrapRows(query)
        return result.toList()
    }

    override fun getProfileByNickname(nickName: String): UserProfile? {
        return UserProfile
            .find {
                UserProfilesTable.nickName eq nickName
            }
            .firstOrNull()
    }

    override fun getProfileById(id: Long): UserProfile? {
        return UserProfile.findById(id)
    }

    override fun getProfilesByIds(ids: Collection<EntityID<Long>>): List<UserProfile> {
        val rawIds = ids.map { it.value }
        val query = UserProfilesTable
            .select {
                UserProfilesTable.id inList rawIds
            }
        val result = UserProfile.wrapRows(query)
        return result.toList()
    }

    override fun createProfile(profile: CreateProfileModel): UserProfile {
        return UserProfile.new {
            mail = profile.mail
            phoneNumber = profile.phoneNumber
            passwordEncrypted = profile.passwordEncrypted
            nickName = profile.nickName
            nameAlias = profile.nameAlias
            description = profile.description
            locataion = profile.description
        }
    }

    override fun saveProfile(profile: UserProfile): UserProfile {
        profile.flush()
        return profile
    }

    override fun deleteProfileById(id: Long): UserProfile? {
        val profile = UserProfile.findById(id) ?: return null
        if (profile.isDeleted) {
            throw ProfileAlreadyDeletedException("Profile with id: $id already deleted")
        }
        profile.isDeleted = true
        profile.flush()
        return profile
    }

    override fun deleteProfileByNickname(nickName: String): UserProfile? {
        val profile = getProfileByNickname(nickName) ?: return null
        if (profile.isDeleted) {
            throw ProfileAlreadyDeletedException("Profile with nickname: $nickName already deleted")
        }
        profile.isDeleted = true
        profile.flush()
        return profile
    }

    override fun profileExistsWith(id: Long): Boolean {
        return UserProfilesTable
            .exists {
                UserProfilesTable.id eq id
            }
    }

    override fun profileExistsWith(nickName: String): Boolean {
        return UserProfilesTable
            .exists {
                UserProfilesTable.nickName eq nickName
            }
    }
}