package com.between_freedom_and_space.mono_backend.mailing.entity

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

class UserVerificationCode(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<UserVerificationCode>(UserVerificationCodesTable)

    var email by UserVerificationCodesTable.email

    var verificationCode by UserVerificationCodesTable.verificationCode

    var securityVariable by UserVerificationCodesTable.securityVariable

    var isVerified by UserVerificationCodesTable.isVerified
}