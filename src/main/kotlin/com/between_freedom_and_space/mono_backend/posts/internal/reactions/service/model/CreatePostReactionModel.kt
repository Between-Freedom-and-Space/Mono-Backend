package com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction

data class CreatePostReactionModel(

    val reaction: Reaction,

    val postId: Long,

    val authorId: Long,
)
