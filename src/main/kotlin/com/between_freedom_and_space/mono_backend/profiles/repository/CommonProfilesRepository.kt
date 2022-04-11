package com.between_freedom_and_space.mono_backend.profiles.repository

import com.between_freedom_and_space.mono_backend.profiles.entities.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel

interface CommonProfilesRepository {

    fun getProfileByNickname(nickName: String): UserProfile?

    fun getProfileById(id: Long): UserProfile?

    fun saveProfile(profile: CreateProfileModel): UserProfile

    fun updateProfile(profile: UserProfile): UserProfile

    fun profileExistsWith(id: Long): Boolean

    fun profileExistsWith(nickName: String): Boolean
}