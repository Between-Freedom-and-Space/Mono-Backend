package com.between_freedom_and_space.mono_backend.mailing.service.impl

import com.between_freedom_and_space.mono_backend.mailing.components.EmailSender
import com.between_freedom_and_space.mono_backend.mailing.components.TelephoneSender
import com.between_freedom_and_space.mono_backend.mailing.service.MailingService
import com.between_freedom_and_space.mono_backend.mailing.service.models.Email
import com.between_freedom_and_space.mono_backend.mailing.service.models.TelephoneNumber
import com.between_freedom_and_space.mono_backend.mailing.service.models.VerificationCheckResult

class MailingServiceImpl(
    private val emailSender: EmailSender,
    private val phoneSender: TelephoneSender,
): MailingService {

    override fun sendVerificationCode(email: Email) {
        TODO("Not yet implemented")
    }

    override fun sendVerificationCode(phone: TelephoneNumber) {
        TODO("Not yet implemented")
    }

    override fun checkVerificationCode(email: Email, code: String): VerificationCheckResult {
        TODO("Not yet implemented")
    }

    override fun checkVerificationCode(phone: TelephoneNumber, code: String): VerificationCheckResult {
        TODO("Not yet implemented")
    }
}