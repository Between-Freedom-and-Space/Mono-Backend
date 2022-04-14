package com.between_freedom_and_space.mono_backend.auth.components.plugin

import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

data class UserAuthorityContextElement(
    val authority: UserAuthority
): AbstractCoroutineContextElement(UserAuthorityContextElement) {

    companion object Key: CoroutineContext.Key<UserAuthorityContextElement>
}