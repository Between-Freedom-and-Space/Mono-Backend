package com.between_freedom_and_space.mono_backend.mailing.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.mailing.api.mappers.VerifyResultToResponseMapper
import com.between_freedom_and_space.mono_backend.mailing.api.models.VerifyCodeResponse
import com.between_freedom_and_space.mono_backend.mailing.components.EmailBodyProvider
import com.between_freedom_and_space.mono_backend.mailing.components.EmailSender
import com.between_freedom_and_space.mono_backend.mailing.components.TelephoneSender
import com.between_freedom_and_space.mono_backend.mailing.components.impl.EmailSenderImpl
import com.between_freedom_and_space.mono_backend.mailing.components.impl.TelephoneSenderImpl
import com.between_freedom_and_space.mono_backend.mailing.components.impl.ResourceEmailBodyProvider
import com.between_freedom_and_space.mono_backend.mailing.modules.qualifiers.MailingMappersQualifiers
import com.between_freedom_and_space.mono_backend.mailing.plugin.config.emailSenderConfiguration
import com.between_freedom_and_space.mono_backend.mailing.repository.UserVerificationCodeRepository
import com.between_freedom_and_space.mono_backend.mailing.repository.impl.UserVerificationCodeRepositoryImpl
import com.between_freedom_and_space.mono_backend.mailing.service.MailingVerificationService
import com.between_freedom_and_space.mono_backend.mailing.service.impl.MailingVerificationServiceImpl
import com.between_freedom_and_space.mono_backend.mailing.service.models.VerificationCheckResult
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<VerificationCheckResult, VerifyCodeResponse>>(
        named(MailingMappersQualifiers.VERIFY_RESULT_TO_RESPONSE_MAPPER)
    ) { VerifyResultToResponseMapper() }
}

private val componentsModule = module {
    val application by inject<Application>()
    single { application.emailSenderConfiguration() }

    single { EmailSenderImpl() } bind EmailSender::class
    single { TelephoneSenderImpl() } bind TelephoneSender::class
    single { ResourceEmailBodyProvider() } bind EmailBodyProvider::class
}

private val repositoryModule = module {
    single { UserVerificationCodeRepositoryImpl() } bind UserVerificationCodeRepository::class
}

private val serviceModule = module {
    single { MailingVerificationServiceImpl(get(), get(), get(), get()) } bind MailingVerificationService::class
}

val mailingModule = module {
    includes(mappersModule)
    includes(componentsModule)
    includes(repositoryModule)
    includes(serviceModule)
}