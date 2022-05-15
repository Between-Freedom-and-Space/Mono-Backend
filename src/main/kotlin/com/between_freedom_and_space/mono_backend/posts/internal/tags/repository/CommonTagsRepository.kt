package com.between_freedom_and_space.mono_backend.posts.internal.tags.repository

import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.CreateTagModel

interface CommonTagsRepository {

    fun getAllTags(pageNumber: Int, pageSize: Int): List<PostTag>

    fun getAllTagsWithIds(ids: Collection<Long>): List<PostTag>

    fun getTagById(tagId: Long): PostTag?

    fun getTagByAlias(tagAlias: String): PostTag?

    fun getTagsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<PostTag>

    fun getTagsWithPostId(postId: Long, pageNumber: Int, pageSize: Int): List<PostTag>

    fun createTag(authorId: Long, createModel: CreateTagModel): PostTag

    fun createTag(createModel: CreateTagModel): PostTag

    fun deleteTag(tagAlias: String): PostTag?

    fun deleteTag(tagId: Long): PostTag?

    fun saveTag(tag: PostTag): PostTag

    fun tagExists(tagAlias: String): Boolean
}