package com.between_freedom_and_space.mono_backend.auth.security.impl

import com.between_freedom_and_space.mono_backend.auth.security.PasswordCipher
import org.apache.commons.codec.binary.Hex
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class PBKDF2PasswordCipher: PasswordCipher {

    companion object {

        private const val RESULT_LENGTH = 512
        private const val ALGORITHM_ALIAS = "PBKDF2WithHmacSHA512"
    }

    override fun encryptPassword(password: String, salt: String, iterations: Int): String {
        val passwordChars = password.toCharArray()
        val saltBytes = salt.toByteArray()

        val hashedBytes = hashPassword(passwordChars, saltBytes, iterations)
        return Hex.encodeHexString(hashedBytes)
    }

    private fun hashPassword(password: CharArray, salt: ByteArray, iterations: Int): ByteArray {
        val keyFactory = SecretKeyFactory.getInstance(ALGORITHM_ALIAS)
        val spec = PBEKeySpec(password, salt, iterations, RESULT_LENGTH)
        val key = keyFactory.generateSecret(spec)
        return key.encoded
    }
}