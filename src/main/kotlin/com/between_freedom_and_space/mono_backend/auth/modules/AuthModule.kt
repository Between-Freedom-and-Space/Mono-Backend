package com.between_freedom_and_space.mono_backend.auth.modules

import com.between_freedom_and_space.mono_backend.auth.api.mappers.AuthenticateResultToAuthenticateResponseMapper
import com.between_freedom_and_space.mono_backend.auth.api.mappers.TokenVerifyResultToVerifyResponseMapper
import com.between_freedom_and_space.mono_backend.auth.api.mappers.UserModelToRegisterResponseMapper
import com.between_freedom_and_space.mono_backend.auth.api.models.AuthenticateUserResponse
import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserResponse
import com.between_freedom_and_space.mono_backend.auth.api.models.TokenVerifyResultResponse
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer.ProducerResult
import com.between_freedom_and_space.mono_backend.auth.components.TokenVerifier
import com.between_freedom_and_space.mono_backend.auth.components.UserPasswordEncryptor
import com.between_freedom_and_space.mono_backend.auth.components.impl.JWTTokenProducer
import com.between_freedom_and_space.mono_backend.auth.components.impl.JWTTokenVerifier
import com.between_freedom_and_space.mono_backend.auth.components.impl.PBKDF2UserPasswordEncryptor
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult
import com.between_freedom_and_space.mono_backend.auth.security.JWTCreator
import com.between_freedom_and_space.mono_backend.auth.security.JWTVerifier
import com.between_freedom_and_space.mono_backend.auth.security.PasswordCipher
import com.between_freedom_and_space.mono_backend.auth.security.impl.HS256Creator
import com.between_freedom_and_space.mono_backend.auth.security.impl.HS256Verifier
import com.between_freedom_and_space.mono_backend.auth.security.impl.PBKDF2PasswordCipher
import com.between_freedom_and_space.mono_backend.auth.service.TokenAuthService
import com.between_freedom_and_space.mono_backend.auth.service.UserAuthService
import com.between_freedom_and_space.mono_backend.auth.service.impl.CommonUserAuthService
import com.between_freedom_and_space.mono_backend.auth.service.impl.JWTTokenAuthService
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.models.UserProfileModel
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<TokenVerifyResult, TokenVerifyResultResponse>> { TokenVerifyResultToVerifyResponseMapper() }
    single<ModelMapper<UserProfileModel, RegisterUserResponse>> { UserModelToRegisterResponseMapper() }
    single<ModelMapper<ProducerResult, AuthenticateUserResponse>> { AuthenticateResultToAuthenticateResponseMapper() }
}

private val securityModule = module {
    single { HS256Creator() } bind JWTCreator::class
    single { HS256Verifier() } bind JWTVerifier::class
    single { PBKDF2PasswordCipher() } bind PasswordCipher::class
}

private val componentsModule = module {
    single { JWTTokenVerifier(get(), get()) } bind TokenVerifier::class
    single { JWTTokenProducer(get(), get()) } bind TokenProducer::class
    single { PBKDF2UserPasswordEncryptor(get()) } bind UserPasswordEncryptor::class
}

val authModule = module {
    includes(mappersModule)
    includes(securityModule)
    includes(componentsModule)

    single { JWTTokenAuthService(get(), get(), get(), get()) } bind TokenAuthService::class
    single { CommonUserAuthService() } bind UserAuthService::class
}