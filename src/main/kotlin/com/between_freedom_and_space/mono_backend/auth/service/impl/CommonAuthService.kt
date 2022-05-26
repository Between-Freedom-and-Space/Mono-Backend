package com.between_freedom_and_space.mono_backend.auth.service.impl

import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserRequest
import com.between_freedom_and_space.mono_backend.auth.components.TokenParser
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer.ProducerResult
import com.between_freedom_and_space.mono_backend.auth.components.TokenVerifier
import com.between_freedom_and_space.mono_backend.auth.components.UserPasswordEncryptor
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.AuthenticateException
import com.between_freedom_and_space.mono_backend.auth.components.exceptions.InvalidTokenException
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult
import com.between_freedom_and_space.mono_backend.auth.service.AuthService
import com.between_freedom_and_space.mono_backend.auth.service.UserProfileAuthService
import com.between_freedom_and_space.mono_backend.auth.service.mappers.RegisterUserRequestToCreatModelMapper
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.services.InteractionProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel

class CommonAuthService(
    private val tokenVerifier: TokenVerifier,
    private val tokenProducer: TokenProducer,
    private val tokenParser: TokenParser,
    private val userAuthService: UserProfileAuthService,
    private val profileService: InteractionProfilesService,
    private val userPasswordEncryptor: UserPasswordEncryptor,
    private val registerUserMapper: ModelMapper<RegisterUserRequest, CreateProfileModel>
): AuthService {

    override fun authenticateUser(nickname: String, passwordEncoded: String): ProducerResult {
        val user = userAuthService.getProfileOrNull(nickname)
            ?: throw AuthenticateException("User with nickname: $nickname not found")

        // TODO(fix this)
//        val encryptedPassword = userPasswordEncryptor.encryptUserPassword(
//            user.id, user.nickName, passwordEncoded
//        )

        if (user.passwordEncrypted != passwordEncoded) {
            throw AuthenticateException("Invalid user password")
        }

        return tokenProducer.produceTokens(user.id, nickname)
    }

    override fun registerNewUser(user: RegisterUserRequest): BaseProfileModel {
        val createProfileModel = registerUserMapper.map(user)
        return profileService.createProfile(createProfileModel)
    }

    override fun deleteUser(accessToken: String): BaseProfileModel {
        val verifyResult = tokenVerifier.verifyAccessToken(accessToken)
        if (verifyResult !is TokenVerifyResult.Valid) {
            throw InvalidTokenException("Access token is not valid", accessToken)
        }

        val decodedToken = verifyResult.decodedToken
        val userAuthority = tokenParser.parseToken(decodedToken)

        return profileService.deleteProfile(userAuthority.userId)
    }
}