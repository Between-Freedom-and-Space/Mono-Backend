package com.between_freedom_and_space.mono_backend.auth.components.plugin

import com.between_freedom_and_space.mono_backend.auth.components.exceptions.AuthenticateException
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority
import kotlin.coroutines.coroutineContext

object AuthenticateContext {

    suspend fun getAuthority(): UserAuthority {
        val element = coroutineContext[UserAuthorityContextElement]
        return element?.authority ?: throw AuthenticateException("User wasn't authenticated")
    }

    internal suspend fun putAuthority(authority: UserAuthority) {
        val contextElement = UserAuthorityContextElement(authority)

    }
}