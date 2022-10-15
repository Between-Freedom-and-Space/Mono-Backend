package com.between_freedom_and_space.mono_backend.mailing.repository

import com.between_freedom_and_space.mono_backend.mailing.entity.UserVerificationCode

interface UserVerificationCodeRepository {

    fun saveNewVerificationCode(
        emailAddress: String, code: String, securityVariable: String
    ): UserVerificationCode

    fun findVerificationCode(
        emailAddress: String, code: String, securityVariable: String
    ): UserVerificationCode?

    fun save(userVerificationCode: UserVerificationCode): UserVerificationCode
}