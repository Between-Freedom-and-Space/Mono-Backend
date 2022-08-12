package com.between_freedom_and_space.mono_backend.profiles.internal.icon.services

import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.ProfileIconUser
import org.jetbrains.exposed.dao.id.EntityID

interface InformationProfileIconService {

    fun getAllProfileIcons(pageNumber: Int, pageSize: Int): List<BaseProfileIconModel>

    fun getProfileIconById(id: Long): BaseProfileIconModel

    fun getProfileIconUser(iconId: Long): ProfileIconUser

    fun getUserProfileIconOrNull(userId: EntityID<Long>): BaseProfileIconModel?
}