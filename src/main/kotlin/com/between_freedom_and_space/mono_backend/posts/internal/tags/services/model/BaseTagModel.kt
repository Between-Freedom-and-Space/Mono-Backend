package com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model

import kotlinx.datetime.LocalDateTime

data class BaseTagModel(

    val id: Long,

    val authorId: Long?,

    val alias: String,

    val description: String?,

    val isDeleted: Boolean,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime,
)
