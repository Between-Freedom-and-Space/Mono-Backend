package com.between_freedom_and_space.mono_backend.access.service.models

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import kotlinx.datetime.LocalDateTime

data class BaseUserRoleModel(

    val id: Long,

    val role: Role,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime,
)
