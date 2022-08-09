package com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.components.UserProfileIdProvider
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.entities.ProfileIcon
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.repository.CommonProfileIconRepository
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.repository.model.CreateProfileIconEntityModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.InteractionProfileIconService
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.exceptions.ProfileIconNotFoundException
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.CreateProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.UpdateProfileIconModel
import org.jetbrains.exposed.sql.transactions.transaction

class InteractionProfileIconServiceImpl(
    private val repository: CommonProfileIconRepository,
    private val profileIdProvider: UserProfileIdProvider,
    private val entityMapper: ModelMapper<ProfileIcon, BaseProfileIconModel>,
    private val createMapper: ModelMapper<CreateProfileIconModel, CreateProfileIconEntityModel>
): InteractionProfileIconService {

    override fun createProfileIcon(createModel: CreateProfileIconModel): BaseProfileIconModel {
        val icon = transaction {
            val profileId = profileIdProvider.getUser(createModel.profileId)
            val createEntityModel = createMapper.map(createModel)
            repository.createProfileIcon(profileId, createEntityModel)
        }
        return entityMapper.map(icon)
    }

    override fun updateProfileIcon(iconId: Long, updateModel: UpdateProfileIconModel): BaseProfileIconModel {
        val icon = transaction {
            val targetIcon = repository.getProfileIconById(iconId)
                ?: throw ProfileIconNotFoundException("Profile icon with id: $iconId not found")

            updateModel.newIconBase64?.let { targetIcon.iconBase64 = it }

            repository.saveProfileIcon(targetIcon)
        }
        return entityMapper.map(icon)
    }

    override fun deleteProfileIcon(iconId: Long) {
        transaction {
            repository.deleteProfileIcon(iconId)
        }
    }
}