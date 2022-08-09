package com.between_freedom_and_space.mono_backend.profiles.internal.icon.repository

import com.between_freedom_and_space.mono_backend.profiles.internal.icon.entities.ProfileIcon
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.repository.model.CreateProfileIconEntityModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.CreateProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.UpdateProfileIconModel
import org.jetbrains.exposed.dao.id.EntityID

interface CommonProfileIconRepository {

    fun getAllProfileIcons(pageNumber: Int, pageSize: Int): List<ProfileIcon>

    fun getProfileIconById(id: Long): ProfileIcon?

    fun createProfileIcon(profileId: EntityID<Long>, model: CreateProfileIconEntityModel): ProfileIcon

    fun saveProfileIcon(icon: ProfileIcon): ProfileIcon

    fun deleteProfileIcon(iconId: Long)
}