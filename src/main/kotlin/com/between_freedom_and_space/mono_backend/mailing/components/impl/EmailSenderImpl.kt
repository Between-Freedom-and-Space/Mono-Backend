package com.between_freedom_and_space.mono_backend.mailing.components.impl

import com.between_freedom_and_space.mono_backend.mailing.components.EmailSender

class EmailSenderImpl: EmailSender {

    override fun sendEmail(targetAddress: String, messageTopic: String, bodyPayload: String): Boolean {
        TODO("Not yet implemented")
    }
}