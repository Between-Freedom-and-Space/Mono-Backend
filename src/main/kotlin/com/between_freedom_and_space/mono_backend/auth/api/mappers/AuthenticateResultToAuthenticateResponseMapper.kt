package com.between_freedom_and_space.mono_backend.auth.api.mappers

import com.between_freedom_and_space.mono_backend.auth.api.models.AuthenticateUserResponse
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer.ProducerResult
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper

class AuthenticateResultToAuthenticateResponseMapper: ModelMapper<ProducerResult, AuthenticateUserResponse> {

    override fun map(original: ProducerResult): AuthenticateUserResponse {
        return AuthenticateUserResponse(
            accessToken = original.accessToken,
            refreshToken = original.refreshToken,
        )
    }
}