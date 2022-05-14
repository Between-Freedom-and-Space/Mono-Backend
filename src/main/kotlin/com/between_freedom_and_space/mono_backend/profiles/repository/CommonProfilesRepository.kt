package com.between_freedom_and_space.mono_backend.profiles.repository

import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel
import org.jetbrains.exposed.dao.id.EntityID

interface CommonProfilesRepository {

    fun getAllProfiles(pageNumber: Int, pageSize: Int): List<UserProfile>

    fun getProfileByNickname(nickName: String): UserProfile?

    fun getProfileById(id: Long): UserProfile?

    fun getProfilesByIds(ids: Collection<EntityID<Long>>): List<UserProfile>

    fun createProfile(profile: CreateProfileModel): UserProfile

    fun saveProfile(profile: UserProfile): UserProfile

    fun deleteProfileById(id: Long): UserProfile?

    fun deleteProfileByNickname(nickName: String): UserProfile?

    fun profileExistsWith(id: Long): Boolean

    fun profileExistsWith(nickName: String): Boolean
}