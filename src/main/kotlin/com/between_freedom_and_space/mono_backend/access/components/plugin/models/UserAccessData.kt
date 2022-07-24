package com.between_freedom_and_space.mono_backend.access.components.plugin.models

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority
import io.ktor.server.request.*

data class UserAccessData(

    val authority: UserAuthority?,

    val request: ApplicationRequest,

    val pathParams: Map<String, String>,

    val role: Role,
) {

    fun getPathParameter(name: String): String? {
        return pathParams[name]
    }
}
