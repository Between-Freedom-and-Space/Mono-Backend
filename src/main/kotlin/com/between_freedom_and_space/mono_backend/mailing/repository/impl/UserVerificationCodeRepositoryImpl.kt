package com.between_freedom_and_space.mono_backend.mailing.repository.impl

import com.between_freedom_and_space.mono_backend.mailing.entity.UserVerificationCode
import com.between_freedom_and_space.mono_backend.mailing.entity.UserVerificationCodesTable
import com.between_freedom_and_space.mono_backend.mailing.repository.UserVerificationCodeRepository
import org.jetbrains.exposed.sql.and

class UserVerificationCodeRepositoryImpl: UserVerificationCodeRepository {

    override fun saveNewVerificationCode(
        emailAddress: String, code: String, securityVariable: String
    ): UserVerificationCode {
        return UserVerificationCode.new {
            email = emailAddress
            verificationCode = code
            this.securityVariable = securityVariable
            isVerified = false
        }
    }

    override fun findVerificationCode(
        emailAddress: String, code: String, securityVariable: String
    ): UserVerificationCode? {
        return UserVerificationCode.find {
            UserVerificationCodesTable.verificationCode.eq(code) and
                    UserVerificationCodesTable.securityVariable.eq(securityVariable) and
                    UserVerificationCodesTable.email.eq(emailAddress)

        }.firstOrNull()
    }

    override fun save(userVerificationCode: UserVerificationCode): UserVerificationCode {
        userVerificationCode.flush()
        return userVerificationCode
    }
}