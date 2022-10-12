package com.between_freedom_and_space.mono_backend.mailing.components

interface TelephoneSender {

    fun sendSms(telephoneNumber: String, messageText: String): Boolean
}