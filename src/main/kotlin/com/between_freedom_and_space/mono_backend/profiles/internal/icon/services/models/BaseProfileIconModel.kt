package com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models

import kotlinx.datetime.LocalDateTime

data class BaseProfileIconModel(

    val id: Long,

    val iconBase64: String,

    val profileId: Long,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime
)
