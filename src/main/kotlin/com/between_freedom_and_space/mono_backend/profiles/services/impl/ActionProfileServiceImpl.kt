package com.between_freedom_and_space.mono_backend.profiles.services.impl

import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.services.ActionProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.models.SubscribeActionResult
import com.between_freedom_and_space.mono_backend.profiles.services.models.enums.ProfileExistsResult

class ActionProfileServiceImpl(
    private val commonRepository: CommonProfilesRepository,
): ActionProfilesService {

    override fun profileExists(nickName: String): ProfileExistsResult {
        TODO("Not yet implemented")
    }

    override fun subscribeTo(authorId: Long, targetProfileNickName: String): SubscribeActionResult {
        TODO("Not yet implemented")
    }

    override fun unsubscribeFrom(authorId: Long, targetProfileNickName: String): SubscribeActionResult {
        TODO("Not yet implemented")
    }
}