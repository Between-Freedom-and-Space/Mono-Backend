package com.between_freedom_and_space.mono_backend.mailing.components.impl

import com.between_freedom_and_space.mono_backend.mailing.components.EmailBodyProvider
import com.between_freedom_and_space.mono_backend.mailing.components.model.SendVerificationCodeBodyParams

class ResourceEmailBodyProvider: EmailBodyProvider {

    override fun provideSendVerificationCodeBody(params: SendVerificationCodeBodyParams): String {
        return """
            <p>&nbsp;</p>
            <h1 style="color: #5e9ca0;">Your authorization code is: <span style="color: #2b2301;">${params.verificationCode}</span></h1>
            <p>&nbsp;</p>
        """.trimIndent()
    }
}