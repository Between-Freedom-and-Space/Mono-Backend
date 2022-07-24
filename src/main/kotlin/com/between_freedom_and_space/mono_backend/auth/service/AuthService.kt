package com.between_freedom_and_space.mono_backend.auth.service

import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserRequest
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer.ProducerResult
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel

interface AuthService {

    fun authenticateUser(nickname: String, passwordEncoded: String): ProducerResult

    fun registerNewUser(user: RegisterUserRequest): BaseProfileModel

    fun deleteUser(accessToken: String): BaseProfileModel
}