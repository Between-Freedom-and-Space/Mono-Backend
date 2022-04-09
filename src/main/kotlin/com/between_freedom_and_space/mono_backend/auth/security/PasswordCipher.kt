package com.between_freedom_and_space.mono_backend.auth.security

interface PasswordCipher {

    fun encryptPassword(password: String): String

    fun decryptPassword(encrypted: String): String
}