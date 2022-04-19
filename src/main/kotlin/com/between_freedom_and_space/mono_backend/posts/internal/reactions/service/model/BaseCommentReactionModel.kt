package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import kotlinx.datetime.LocalDateTime

data class BaseCommentReactionModel(

    val id: Long,

    val reaction: Reaction,

    val commentId: Long,

    val userId: Long,

    val isDeleted: Boolean,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime,
)
