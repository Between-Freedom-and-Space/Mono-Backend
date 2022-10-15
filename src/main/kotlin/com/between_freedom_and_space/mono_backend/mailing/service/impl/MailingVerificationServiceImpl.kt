package com.between_freedom_and_space.mono_backend.mailing.service.impl

import com.between_freedom_and_space.mono_backend.mailing.components.EmailBodyProvider
import com.between_freedom_and_space.mono_backend.mailing.components.EmailSender
import com.between_freedom_and_space.mono_backend.mailing.components.TelephoneSender
import com.between_freedom_and_space.mono_backend.mailing.components.constants.EmailTopicConstants
import com.between_freedom_and_space.mono_backend.mailing.components.model.SendVerificationCodeBodyParams
import com.between_freedom_and_space.mono_backend.mailing.repository.UserVerificationCodeRepository
import com.between_freedom_and_space.mono_backend.mailing.service.MailingVerificationService
import com.between_freedom_and_space.mono_backend.mailing.service.models.Email
import com.between_freedom_and_space.mono_backend.mailing.service.models.TelephoneNumber
import com.between_freedom_and_space.mono_backend.mailing.service.models.VerificationCheckResult
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class MailingVerificationServiceImpl(
    private val emailSender: EmailSender,
    private val phoneSender: TelephoneSender,
    private val emailBodyProvider: EmailBodyProvider,
    private val verificationCodeRepository: UserVerificationCodeRepository,
): MailingVerificationService {

    override fun sendVerificationCode(email: Email, securityVariable: String) {
        transaction {
            val code = generateVerificationCode()
            val bodyPayload = emailBodyProvider.provideSendVerificationCodeBody(
                SendVerificationCodeBodyParams(verificationCode = code)
            )
            verificationCodeRepository.saveNewVerificationCode(
                email.address, code, securityVariable
            )
            emailSender.sendEmailOrThrow(
                email.address, EmailTopicConstants.SEND_VERIFICATION_CODE_TOPIC, bodyPayload
            )
        }
    }

    override fun sendVerificationCode(phone: TelephoneNumber, securityVariable: String) {
        TODO("Not yet implemented")
    }

    override fun checkVerificationCode(
        email: Email, code: String, securityVariable: String
    ): VerificationCheckResult {
        return transaction {
            val verificationCodeInstance = verificationCodeRepository.findVerificationCode(
                email.address, code, securityVariable
            )

            if (verificationCodeInstance == null) {
                VerificationCheckResult.Invalid
            } else {
                verificationCodeInstance.isVerified = true
                verificationCodeRepository.save(verificationCodeInstance)
                VerificationCheckResult.Valid
            }
        }
    }

    override fun checkVerificationCode(
        phone: TelephoneNumber, code: String, securityVariable: String
    ): VerificationCheckResult {
        TODO("Not yet implemented")
    }

    private fun generateVerificationCode(): String {
        return UUID.randomUUID().toString()
    }
}