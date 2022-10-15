package com.between_freedom_and_space.mono_backend.mailing.service

import com.between_freedom_and_space.mono_backend.mailing.service.models.Email
import com.between_freedom_and_space.mono_backend.mailing.service.models.TelephoneNumber
import com.between_freedom_and_space.mono_backend.mailing.service.models.VerificationCheckResult

interface MailingService {

    fun sendVerificationCode(email: Email)

    fun sendVerificationCode(phone: TelephoneNumber)

    fun checkVerificationCode(email: Email, code: String): VerificationCheckResult

    fun checkVerificationCode(phone: TelephoneNumber, code: String): VerificationCheckResult
}