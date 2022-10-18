package com.between_freedom_and_space.mono_backend.mailing.api.routing

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.mailing.api.models.SendEmailVerificationCodeRequest
import com.between_freedom_and_space.mono_backend.mailing.api.models.VerifyCodeResponse
import com.between_freedom_and_space.mono_backend.mailing.api.models.VerifyEmailVerificationCodeRequest
import com.between_freedom_and_space.mono_backend.mailing.modules.qualifiers.MailingMappersQualifiers
import com.between_freedom_and_space.mono_backend.mailing.service.MailingVerificationService
import com.between_freedom_and_space.mono_backend.mailing.service.models.Email
import com.between_freedom_and_space.mono_backend.mailing.service.models.VerificationCheckResult
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendEmptyResponse
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.emailMailingRouting() {
    val basePath = "/mailing/email"

    routing {

        val mailingService by inject<MailingVerificationService>()

        val mapper by inject<ModelMapper<VerificationCheckResult, VerifyCodeResponse>>(
            named(MailingMappersQualifiers.VERIFY_RESULT_TO_RESPONSE_MAPPER)
        )

        post("$basePath/verificationCode/send") {
            val request = validateAndReceiveRequest<SendEmailVerificationCodeRequest>()
            val email = Email(request.email)
            val securityVariable = request.securityVariable

            mailingService.sendVerificationCode(email, securityVariable)

            sendEmptyResponse()
        }

        post("$basePath/verificationCode/check") {
            val request = validateAndReceiveRequest<VerifyEmailVerificationCodeRequest>()
            val email = Email(request.targetEmail)
            val code = request.verificationCode
            val securityVariable = request.securityVariable

            val checkResult = mailingService.checkVerificationCode(email, code, securityVariable)

            val checkResponse = mapper.map(checkResult)
            val response = Response.ok(checkResponse)

            sendResponse(response)
        }
    }
}