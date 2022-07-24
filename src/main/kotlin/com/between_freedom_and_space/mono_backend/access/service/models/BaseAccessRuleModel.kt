package com.between_freedom_and_space.mono_backend.access.service.models

import kotlinx.datetime.LocalDateTime

data class BaseAccessRuleModel(

    val id: Long,

    val pathPattern: String,

    val modifiedByUserId: Long?,

    val isActive: Boolean,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime,
)
