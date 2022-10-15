package com.between_freedom_and_space.mono_backend.mailing.components

interface EmailSender {

    fun sendEmail(
        targetAddress: String, messageTopic: String, bodyPayload: String
    ): Boolean

    fun sendEmailOrThrow(
        targetAddress: String, messageTopic: String, bodyPayload: String
    )
}