package com.between_freedom_and_space.mono_backend.posts.services.models

import kotlinx.datetime.LocalDateTime

data class BasePostModel(

    val id: Long,

    val text: String,

    val authorId: Long,

    val commentsIds: List<Long>,

    val reactionsIds: List<Long>,

    val tagsIds: List<Long>,

    val isVisible: Boolean,

    val isDeleted: Boolean,

    val isEdited: Boolean,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime,
)
