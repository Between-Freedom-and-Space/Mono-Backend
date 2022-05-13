package com.between_freedom_and_space.mono_backend.posts.internal.tags.services

import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.TagId

interface InformationTagsService {

    fun getAllTags(pageNumber: Int, pageSize: Int): List<BaseTagModel>

    fun getTagById(tagId: Long): BaseTagModel

    fun getTagByAlias(tagAlias: String): BaseTagModel

    fun getTags(ids: Collection<TagId>): List<BaseTagModel>

    fun getTagsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<BaseTagModel>

    fun getTagsWithPostId(postId: Long, pageNumber: Int, pageSize: Int): List<BaseTagModel>
}