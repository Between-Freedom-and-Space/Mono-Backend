package com.between_freedom_and_space.mono_backend.posts.services.models

data class CreatePostModel(

    val name: String,

    val text: String,

    val isVisible: Boolean,

    val tagsAliases: List<String>,
)
