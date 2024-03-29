package com.between_freedom_and_space.mono_backend.mailing.entity

import org.jetbrains.exposed.dao.id.LongIdTable

object UserVerificationCodesTable: LongIdTable("user_verification_codes") {

    val email = varchar("email", length = 255)

    val verificationCode = varchar("verification_code", length = 255)

    val securityVariable = varchar("security_variable", length = 512)

    val isVerified = bool("is_verified").clientDefault { false }
}