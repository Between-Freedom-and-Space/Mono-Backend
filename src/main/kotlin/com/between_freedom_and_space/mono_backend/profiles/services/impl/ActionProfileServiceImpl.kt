package com.between_freedom_and_space.mono_backend.profiles.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.InteractionProfileIconService
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.CreateProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonSubscriptionsRepository
import com.between_freedom_and_space.mono_backend.profiles.services.ActionProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.ProfileNotFoundException
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.SubscribeActionResult
import com.between_freedom_and_space.mono_backend.profiles.services.models.SubscribeActionResult.ActionResult.FAILURE
import com.between_freedom_and_space.mono_backend.profiles.services.models.SubscribeActionResult.ActionResult.SUCCESS
import com.between_freedom_and_space.mono_backend.profiles.services.models.enums.ProfileExistsResult
import com.between_freedom_and_space.mono_backend.profiles.services.models.enums.ProfileExistsResult.PROFILE_EXISTS
import com.between_freedom_and_space.mono_backend.profiles.services.models.enums.ProfileExistsResult.PROFILE_NOT_FOUND
import org.jetbrains.exposed.sql.not
import org.jetbrains.exposed.sql.transactions.transaction

class ActionProfileServiceImpl(
    private val profileRepository: CommonProfilesRepository,
    private val subscriptionRepository: CommonSubscriptionsRepository,
    private val iconInteractionService: InteractionProfileIconService,
    private val entityMapper: ModelMapper<UserProfile, BaseProfileModel>,
): ActionProfilesService {

    override fun profileExists(nickName: String): ProfileExistsResult {
        val exists = transaction {
            profileRepository.profileExistsWith(nickName)
        }
        return if (exists) {
            PROFILE_EXISTS
        } else {
            PROFILE_NOT_FOUND
        }
    }

    override fun subscribeTo(authorId: Long, targetProfileNickName: String): SubscribeActionResult {
        val (result, message) = transaction {
            val authorProfile = profileRepository.getProfileById(authorId)
                ?: throw ProfileNotFoundException("Author profile with id: $authorId not found")
            val targetProfile = profileRepository.getProfileByNickname(targetProfileNickName)
                ?: throw ProfileNotFoundException("Target profile with nickname: $targetProfileNickName not found")

            val result = subscriptionRepository.subscribeProfile(authorProfile, targetProfile)
            return@transaction if (result != null) {
                true to "Success subscribe"
            } else {
                false to "Failure subscribe"
            }
        }

        return SubscribeActionResult(
            result = if (result) SUCCESS else FAILURE,
            message = message,
        )
    }

    override fun unsubscribeFrom(authorId: Long, targetProfileNickName: String): SubscribeActionResult {
        val (result, message) = transaction {
            val authorProfile = profileRepository.getProfileById(authorId)
                ?: throw ProfileNotFoundException("Author profile with id: $authorId not found")
            val targetProfile = profileRepository.getProfileByNickname(targetProfileNickName)
                ?: throw ProfileNotFoundException("Target profile with nickname: $targetProfileNickName not found")

            val result = subscriptionRepository.unsubscribeProfile(authorProfile, targetProfile)
            return@transaction if (result != null) {
                true to "Success subscribe"
            } else {
                false to "Failure subscribe"
            }
        }

        return SubscribeActionResult(
            result = if (result) SUCCESS else FAILURE,
            message = message,
        )
    }

    override fun setProfileIcon(nickName: String, iconBase64: String): BaseProfileIconModel {
        return transaction {
            val profile = getProfileOrThrow(nickName)

            val createModel = CreateProfileIconModel(
                iconBase64 = iconBase64,
                profileId = profile.id.value,
            )
            iconInteractionService.createProfileIcon(createModel)
        }
    }

    private fun getProfileOrThrow(nickname: String): UserProfile {
        return profileRepository.getProfileByNickname(nickname)
            ?: throw ProfileNotFoundException("Profile with nickname: $nickname not found")
    }
}