package com.between_freedom_and_space.mono_backend.auth.components.impl

import com.between_freedom_and_space.mono_backend.auth.components.UserPasswordEncryptor
import com.between_freedom_and_space.mono_backend.auth.security.PasswordCipher
import kotlin.math.abs

class PBKDF2UserPasswordEncryptor(
    private val passwordCipher: PasswordCipher,
): UserPasswordEncryptor {

    companion object {

        private const val ADDITIONAL_SALT = "Between freedom and space"
        private const val SALT_LENGTH = 150

        private const val ITERATIONS_OFFSET = 200
        private const val ITERATIONS_MIN_COUNT = 20
    }

    override fun encryptUserPassword(userId: Long, userNickname: String, password: String): String {
        val salt = generateSalt(userNickname)
        val iterationsCount = getIterationsCount(userId)
        return passwordCipher.encryptPassword(password, salt, iterationsCount)
    }

    private fun generateSalt(nickname: String): String {
        val salt = StringBuilder()
        repeat(SALT_LENGTH) {
            salt.append(nickname)
        }
        return salt.take(SALT_LENGTH).toString().plus(ADDITIONAL_SALT)
    }

    private fun getIterationsCount(userId: Long): Int {
        return abs((userId + ITERATIONS_OFFSET) % ITERATIONS_OFFSET + ITERATIONS_MIN_COUNT).toInt()
    }
}