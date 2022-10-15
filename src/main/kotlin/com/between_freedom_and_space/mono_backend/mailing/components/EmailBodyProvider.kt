package com.between_freedom_and_space.mono_backend.mailing.components

import com.between_freedom_and_space.mono_backend.mailing.components.model.SendVerificationCodeBodyParams

interface EmailBodyProvider {

    fun provideSendVerificationCodeBody(params: SendVerificationCodeBodyParams): String
}