package com.between_freedom_and_space.mono_backend.posts.internal.tags.repository

import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag

interface CommonTagsRepository {

    fun getAllTags(pageNumber: Int, pageSize: Int): List<PostTag>

    fun getTagById(tagId: Long): PostTag
}