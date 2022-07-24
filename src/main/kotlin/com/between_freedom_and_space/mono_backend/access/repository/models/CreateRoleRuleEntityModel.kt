package com.between_freedom_and_space.mono_backend.access.repository.models

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import org.jetbrains.exposed.dao.id.EntityID

data class CreateRoleRuleEntityModel(

    val ruleId: Long,

    val role: Role,

    val isActive: Boolean,
)
