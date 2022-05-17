package com.between_freedom_and_space.mono_backend.posts.repository.models

data class CreatePostEntityModel(

    val name: String,

    val text: String,

    val isVisible: Boolean
)
