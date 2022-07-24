package com.between_freedom_and_space.mono_backend.access.api.models

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangeUserRoleRequest(

    @SerialName("new_role")
    val newRole: Role,
)
