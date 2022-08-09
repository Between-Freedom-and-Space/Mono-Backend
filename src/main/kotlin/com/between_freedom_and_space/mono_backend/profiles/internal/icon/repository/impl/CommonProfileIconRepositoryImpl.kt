package com.between_freedom_and_space.mono_backend.profiles.internal.icon.repository.impl

import com.between_freedom_and_space.mono_backend.profiles.internal.icon.entities.ProfileIcon
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.entities.ProfileIconsTable
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.repository.CommonProfileIconRepository
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.repository.model.CreateProfileIconEntityModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.CreateProfileIconModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.selectAll

class CommonProfileIconRepositoryImpl: CommonProfileIconRepository {

    override fun getAllProfileIcons(pageNumber: Int, pageSize: Int): List<ProfileIcon> {
        val offset = (pageNumber - 1).toLong() * pageSize
        val query = ProfileIconsTable
            .selectAll()
            .orderBy(ProfileIconsTable.createdDate, SortOrder.DESC_NULLS_LAST)
            .limit(pageSize, offset)
        val result = ProfileIcon.wrapRows(query)
        return result.toList()
    }

    override fun getProfileIconById(id: Long): ProfileIcon? {
        return ProfileIcon.findById(id)
    }

    override fun createProfileIcon(profileId: EntityID<Long>, model: CreateProfileIconEntityModel): ProfileIcon {
        return ProfileIcon.new {
            iconBase64 = model.iconBase64
            profile = profileId
        }
    }

    override fun saveProfileIcon(icon: ProfileIcon): ProfileIcon {
        icon.flush()
        return icon
    }

    override fun deleteProfileIcon(iconId: Long) {
        ProfileIcon.findById(iconId)?.delete()
    }
}