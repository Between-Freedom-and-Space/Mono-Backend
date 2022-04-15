package com.between_freedom_and_space.mono_backend.access.components.exceptions

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority

class AccessException(
    message: String,
    val userRole: Role,
    val authority: UserAuthority? = null,
): RuntimeException(message)