package com.between_freedom_and_space.mono_backend.auth.components

interface UserPasswordEncryptor {

    fun encryptUserPassword(userId: Long, userNickname: String, password: String): String
}