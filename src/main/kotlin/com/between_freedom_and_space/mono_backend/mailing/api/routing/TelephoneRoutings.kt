package com.between_freedom_and_space.mono_backend.mailing.api.routing

import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.mailing.api.models.SendPhoneVerificationCodeRequest
import com.between_freedom_and_space.mono_backend.mailing.api.models.VerifyCodeResponse
import com.between_freedom_and_space.mono_backend.mailing.api.models.VerifyPhoneVerificationCodeRequest
import com.between_freedom_and_space.mono_backend.mailing.service.MailingService
import com.between_freedom_and_space.mono_backend.mailing.service.models.TelephoneNumber
import com.between_freedom_and_space.mono_backend.mailing.service.models.VerificationCheckResult
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendEmptyResponse
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.telephoneMailingRouting() {
    val basePath = "/mailing/telephone"

    routing {

        val mailingService by inject<MailingService>()

        val mapper by inject<ModelMapper<VerificationCheckResult, VerifyCodeResponse>>()

        post("$basePath/verificationCode/send") {
            val request = validateAndReceiveRequest<SendPhoneVerificationCodeRequest>()
            val phone = TelephoneNumber(request.telephoneNumber)

            mailingService.sendVerificationCode(phone)

            sendEmptyResponse()
        }

        post("$basePath/verificationCode/check") {
            val request = validateAndReceiveRequest<VerifyPhoneVerificationCodeRequest>()
            val phone = TelephoneNumber(request.targetPhone)
            val code = request.verificationCode

            val result = mailingService.checkVerificationCode(phone, code)

            val checkResponse = mapper.map(result)
            val response = Response.ok(checkResponse)

            sendResponse(response)
        }
    }
}