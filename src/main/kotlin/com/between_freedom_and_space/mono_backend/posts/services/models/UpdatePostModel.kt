package com.between_freedom_and_space.mono_backend.posts.services.models

data class UpdatePostModel(

    val newName: String? = null,

    val newText: String? = null,

    val newVisibility: Boolean? = null,

    val newTagsAliases: List<String>? = null,
)
