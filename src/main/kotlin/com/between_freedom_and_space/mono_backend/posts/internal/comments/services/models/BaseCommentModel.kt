package com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models

import kotlinx.datetime.LocalDateTime

data class BaseCommentModel(

    val id: Long,

    val postId: Long,

    val authorId: Long,

    val text: String,

    val isDeleted: Boolean,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime,
)
