package com.between_freedom_and_space.mono_backend.auth.modules

import com.between_freedom_and_space.mono_backend.auth.api.mappers.AuthenticateResultToAuthenticateResponseMapper
import com.between_freedom_and_space.mono_backend.auth.api.mappers.TokenVerifyResultToVerifyResponseMapper
import com.between_freedom_and_space.mono_backend.auth.api.mappers.UserModelToRegisterResponseMapper
import com.between_freedom_and_space.mono_backend.auth.api.models.AuthenticateUserResponse
import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserRequest
import com.between_freedom_and_space.mono_backend.auth.api.models.RegisterUserResponse
import com.between_freedom_and_space.mono_backend.auth.api.models.TokenVerifyResultResponse
import com.between_freedom_and_space.mono_backend.auth.components.TokenParser
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer
import com.between_freedom_and_space.mono_backend.auth.components.TokenProducer.ProducerResult
import com.between_freedom_and_space.mono_backend.auth.components.TokenVerifier
import com.between_freedom_and_space.mono_backend.auth.components.UserPasswordEncryptor
import com.between_freedom_and_space.mono_backend.auth.components.impl.JWTTokenParser
import com.between_freedom_and_space.mono_backend.auth.components.impl.JWTTokenProducer
import com.between_freedom_and_space.mono_backend.auth.components.impl.JWTTokenVerifier
import com.between_freedom_and_space.mono_backend.auth.components.impl.PBKDF2UserPasswordEncryptor
import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult
import com.between_freedom_and_space.mono_backend.auth.components.plugin.AuthenticateProcessor
import com.between_freedom_and_space.mono_backend.auth.components.plugin.impl.TokenAuthenticateProcessor
import com.between_freedom_and_space.mono_backend.auth.modules.qualifiers.AuthModelMapperQualifier
import com.between_freedom_and_space.mono_backend.auth.plugins.config.authConfiguration
import com.between_freedom_and_space.mono_backend.auth.security.JWTProcessor
import com.between_freedom_and_space.mono_backend.auth.security.JWTVerifier
import com.between_freedom_and_space.mono_backend.auth.security.PasswordCipher
import com.between_freedom_and_space.mono_backend.auth.security.impl.HS256Processor
import com.between_freedom_and_space.mono_backend.auth.security.impl.HS256Verifier
import com.between_freedom_and_space.mono_backend.auth.security.impl.PBKDF2PasswordCipher
import com.between_freedom_and_space.mono_backend.auth.service.AuthService
import com.between_freedom_and_space.mono_backend.auth.service.TokenAuthService
import com.between_freedom_and_space.mono_backend.auth.service.UserProfileAuthService
import com.between_freedom_and_space.mono_backend.auth.service.impl.CommonAuthService
import com.between_freedom_and_space.mono_backend.auth.service.impl.CommonUserProfileAuthService
import com.between_freedom_and_space.mono_backend.auth.service.impl.JWTTokenAuthService
import com.between_freedom_and_space.mono_backend.auth.service.mappers.ProfileToUserAuthModelMapper
import com.between_freedom_and_space.mono_backend.auth.service.mappers.RegisterUserRequestToCreatModelMapper
import com.between_freedom_and_space.mono_backend.auth.service.model.UserAuthModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<TokenVerifyResult, TokenVerifyResultResponse>>(
        named(AuthModelMapperQualifier.TOKEN_VERIFY_RESULT_TO_RESPONSE)
    ) { TokenVerifyResultToVerifyResponseMapper() }
    single<ModelMapper<BaseProfileModel, RegisterUserResponse>>(
        named(AuthModelMapperQualifier.BASE_PROFILE_MODEL_TO_REGISTER_USER_RESPONSE)
    ) { UserModelToRegisterResponseMapper() }
    single<ModelMapper<ProducerResult, AuthenticateUserResponse>>(
        named(AuthModelMapperQualifier.PRODUCER_RESULT_TO_AUTHENTICATE_RESPONSE)
    ) { AuthenticateResultToAuthenticateResponseMapper() }
    single<ModelMapper<RegisterUserRequest, CreateProfileModel>>(
        named(AuthModelMapperQualifier.REGISTER_USER_REQUEST_TO_MODEL)
    ) {
        RegisterUserRequestToCreatModelMapper()
    }
    single<ModelMapper<UserProfile, UserAuthModel>>(
        named(AuthModelMapperQualifier.USER_PROFILE_TO_AUTH_MODEL)
    ) { ProfileToUserAuthModelMapper() }
}

private val securityModule = module {
    single { HS256Processor() } bind JWTProcessor::class
    single { HS256Verifier() } bind JWTVerifier::class
    single { PBKDF2PasswordCipher() } bind PasswordCipher::class
}

private val pluginModule = module {
    single { TokenAuthenticateProcessor(get(), get(), get()) } bind AuthenticateProcessor::class
}

private val componentsModule = module {
    val application by inject<Application>()
    single { application.authConfiguration() }

    single { JWTTokenParser(get()) } bind TokenParser::class
    single { JWTTokenVerifier(get(), get()) } bind TokenVerifier::class
    single { JWTTokenProducer(get(), get()) } bind TokenProducer::class
    single { PBKDF2UserPasswordEncryptor(get()) } bind UserPasswordEncryptor::class
}

val authModule = module {
    includes(mappersModule)
    includes(securityModule)
    includes(pluginModule)
    includes(componentsModule)

    single { JWTTokenAuthService(get(), get()) } bind TokenAuthService::class
    single {
        CommonAuthService(
            get(), get(), get(), get(), get(), get(), get(),
            get(named(AuthModelMapperQualifier.REGISTER_USER_REQUEST_TO_MODEL))
        )
    } bind AuthService::class
    single {
        CommonUserProfileAuthService(
            get(), get(),
            get(named(AuthModelMapperQualifier.USER_PROFILE_TO_AUTH_MODEL))
        )
    } bind UserProfileAuthService::class
}