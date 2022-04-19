package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import kotlinx.datetime.LocalDateTime

data class BasePostReactionModel(

    val id: Long,

    val postId: Long,

    val userId: Long,

    val reaction: Reaction,

    val isDeleted: Boolean,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime,
)
