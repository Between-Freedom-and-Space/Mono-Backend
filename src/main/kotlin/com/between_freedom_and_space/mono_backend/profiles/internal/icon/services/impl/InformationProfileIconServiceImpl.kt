package com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.entities.ProfileIcon
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.repository.CommonProfileIconRepository
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.InformationProfileIconService
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.exceptions.ProfileIconNotFoundException
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.ProfileIconUser
import org.jetbrains.exposed.sql.transactions.transaction

class InformationProfileIconServiceImpl(
    private val repository: CommonProfileIconRepository,
    private val entityMapper: ModelMapper<ProfileIcon, BaseProfileIconModel>
): InformationProfileIconService {

    override fun getAllProfileIcons(pageNumber: Int, pageSize: Int): List<BaseProfileIconModel> {
        val icons = transaction {
            repository.getAllProfileIcons(pageNumber, pageSize)
        }
        return icons.map { entityMapper.map(it) }
    }

    override fun getProfileIconById(id: Long): BaseProfileIconModel {
        val icon = transaction {
            repository.getProfileIconById(id)
                ?: throw ProfileIconNotFoundException("Profile icon with id: $id not found")
        }
        return entityMapper.map(icon)
    }

    override fun getProfileIconUser(iconId: Long): ProfileIconUser {
        val icon = transaction {
            repository.getProfileIconById(iconId)
                ?: throw ProfileIconNotFoundException("Profile icon with id: $iconId not found")
        }
        return ProfileIconUser(icon.profile.value)
    }
}