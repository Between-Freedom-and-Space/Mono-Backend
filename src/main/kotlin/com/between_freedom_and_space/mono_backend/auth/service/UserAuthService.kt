package com.between_freedom_and_space.mono_backend.auth.service

import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserRequest
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer.ProducerResult
import com.between_freedom_and_space.mono_backend.profiles.models.UserProfileModel

interface UserAuthService {

    fun authenticateUser(nickname: String, passwordEncoded: String): ProducerResult

    fun registerNewUser(user: RegisterUserRequest): UserProfileModel

    fun deleteUser(accessToken: String): UserProfileModel
}