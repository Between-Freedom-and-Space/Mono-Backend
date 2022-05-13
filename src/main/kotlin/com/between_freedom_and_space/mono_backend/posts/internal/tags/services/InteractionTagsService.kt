package com.between_freedom_and_space.mono_backend.posts.internal.tags.services

import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.CreateTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.UpdateTagModel

interface InteractionTagsService {

    fun createTag(authorId: Long, model: CreateTagModel): BaseTagModel

    fun updateTag(tagId: Long, model: UpdateTagModel): BaseTagModel

    fun updateTag(tagAlias: String, model: UpdateTagModel): BaseTagModel

    fun deleteTag(tagId: Long): BaseTagModel

    fun deleteTag(tagAlias: String): BaseTagModel
}