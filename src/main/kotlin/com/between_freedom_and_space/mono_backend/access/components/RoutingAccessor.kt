package com.between_freedom_and_space.mono_backend.access.components

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority

interface RoutingAccessor {

    fun verifyAccess(authority: UserAuthority, rawPath: String): AccessVerifyResult
}