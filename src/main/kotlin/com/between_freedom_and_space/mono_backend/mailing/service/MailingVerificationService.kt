package com.between_freedom_and_space.mono_backend.mailing.service

import com.between_freedom_and_space.mono_backend.mailing.service.models.Email
import com.between_freedom_and_space.mono_backend.mailing.service.models.TelephoneNumber
import com.between_freedom_and_space.mono_backend.mailing.service.models.VerificationCheckResult

interface MailingVerificationService {

    fun sendVerificationCode(email: Email, securityVariable: String)

    fun sendVerificationCode(phone: TelephoneNumber, securityVariable: String)

    fun checkVerificationCode(
        email: Email, code: String, securityVariable: String
    ): VerificationCheckResult

    fun checkVerificationCode(
        phone: TelephoneNumber, code: String, securityVariable: String
    ): VerificationCheckResult
}