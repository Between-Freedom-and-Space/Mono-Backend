package com.between_freedom_and_space.mono_backend.mailing.components.impl

import com.between_freedom_and_space.mono_backend.mailing.components.EmailSender
import com.between_freedom_and_space.mono_backend.mailing.components.model.EmailSenderConfig
import com.between_freedom_and_space.mono_backend.util.support.dateNow
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EmailSenderImpl(
    private val config: EmailSenderConfig
): EmailSender {

    private val session = buildSession()

    override fun sendEmail(targetAddress: String, messageTopic: String, bodyPayload: String): Boolean {
        return try {
            sendEmailOrThrow(targetAddress, messageTopic, bodyPayload)
            true
        } catch (_: Exception) {
            false
        }
    }

    override fun sendEmailOrThrow(targetAddress: String, messageTopic: String, bodyPayload: String) {
        val message = provideEmailMessage().apply {
            setRecipient(Message.RecipientType.TO, addressOf(targetAddress))
            setText(bodyPayload)
            subject = messageTopic
            sentDate = dateNow()
        }

        Transport.send(message)
    }

    private fun provideEmailMessage(): Message {
        return MimeMessage(session).apply {
            setFrom(addressOf(config.authUser))
        }
    }

    private fun addressOf(address: String): Address {
        return InternetAddress(address)
    }

    private fun buildSession(): Session {
        val props = Properties().apply {
            put("mail.smtp.host", config.host)
            put("mail.smtp.ssl.enable", config.secure)
            put("mail.smtp.port", config.port)
            put("mail.smtp.auth", true)
            put("mail.debug", config.debug)
        }
        return Session.getDefaultInstance(props, object: Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(config.authUser, config.authPassword)
            }
        })
    }
}