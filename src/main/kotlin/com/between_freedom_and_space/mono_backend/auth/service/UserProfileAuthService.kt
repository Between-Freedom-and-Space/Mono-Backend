package com.between_freedom_and_space.mono_backend.auth.service

import com.between_freedom_and_space.mono_backend.auth.service.model.UserAuthModel

interface UserProfileAuthService {

    fun getProfileOrNull(nickName: String): UserAuthModel?

    fun getProfileOrNull(id: Long): UserAuthModel?

    fun profileIsValid(nickName: String): Boolean

    fun profileIsValid(id: Long): Boolean
}