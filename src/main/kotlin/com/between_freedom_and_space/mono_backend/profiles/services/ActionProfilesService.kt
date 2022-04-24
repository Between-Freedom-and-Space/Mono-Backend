package com.between_freedom_and_space.mono_backend.profiles.services

import com.between_freedom_and_space.mono_backend.profiles.services.models.SubscribeActionResult
import com.between_freedom_and_space.mono_backend.profiles.services.models.enums.ProfileExistsResult

interface ActionProfilesService {

    fun profileExists(nickName: String): ProfileExistsResult

    fun subscribeTo(authorId: Long, targetProfileNickName: String): SubscribeActionResult

    fun unsubscribeFrom(authorId: Long, targetProfileNickName: String): SubscribeActionResult
}