package com.between_freedom_and_space.mono_backend.posts.internal.tags.services

import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.TagId

interface InteractionPostToTagService {

    fun deleteAllPostTags(postId: Long): List<BaseTagModel>

    fun addTagsToPost(postId: Long, tags: Collection<TagId>): List<BaseTagModel>
}