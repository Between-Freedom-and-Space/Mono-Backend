package com.between_freedom_and_space.mono_backend.profiles.services

import com.between_freedom_and_space.mono_backend.profiles.models.UserProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel

interface InformationProfilesService {

    fun getProfile(userId: Long): UserProfileModel

    fun getProfile(nickname: String): UserProfileModel

    fun getProfileOrNull(userId: Long): UserProfileModel?

    fun getProfileOrNull(nickname: String): UserProfileModel?

    fun createProfile(createModel: CreateProfileModel): UserProfileModel

    fun changeVisibility(userId: Long, isVisible: Boolean): UserProfileModel

    fun changeVisibility(nickname: String, isVisible: Boolean): UserProfileModel

    fun changeVisibilityOrNull(userId: Long, isVisible: Boolean): UserProfileModel?

    fun changeVisibilityOrNull(nickname: String, isVisible: Boolean): UserProfileModel?

    fun markAsDeleted(userProfileId: Long): UserProfileModel

    fun markAsDeleted(nickname: String): UserProfileModel

    fun markAsDeletedOrNull(userProfileId: Long): UserProfileModel?

    fun markAsDeletedOrNull(nickname: String): UserProfileModel?
}