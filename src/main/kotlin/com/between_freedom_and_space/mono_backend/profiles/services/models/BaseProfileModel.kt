package com.between_freedom_and_space.mono_backend.profiles.services.models

import kotlinx.datetime.LocalDateTime

data class BaseProfileModel(

    val id: Long,

    val nickName: String,

    val nameAlias: String,

    val description: String?,

    val location: String?,

    val isVisible: Boolean,

    val isDeleted: Boolean,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime,
)
