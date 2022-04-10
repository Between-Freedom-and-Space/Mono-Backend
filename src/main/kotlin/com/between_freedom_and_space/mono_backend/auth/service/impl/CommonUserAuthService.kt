package com.between_freedom_and_space.mono_backend.auth.service.impl

import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserRequest
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer
import com.between_freedom_and_space.mono_backend.auth.service.UserAuthService
import com.between_freedom_and_space.mono_backend.profiles.models.UserProfileModel

class CommonUserAuthService(

): UserAuthService {

    override fun authenticateUser(nickname: String, passwordEncoded: String): TokenProducer.ProducerResult {
        TODO("Not yet implemented")
    }

    override fun registerNewUser(user: RegisterUserRequest): UserProfileModel {
        TODO("Not yet implemented")
    }

    override fun deleteUser(accessToken: String): UserProfileModel {
        TODO("Not yet implemented")
    }
}