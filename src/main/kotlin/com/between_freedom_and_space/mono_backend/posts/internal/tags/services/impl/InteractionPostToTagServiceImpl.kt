package com.between_freedom_and_space.mono_backend.posts.internal.tags.services.impl

import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InteractionPostToTagService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.TagId

class InteractionPostToTagServiceImpl: InteractionPostToTagService {

    override fun deleteAllPostTags(postId: Long): List<BaseTagModel> {
        TODO("Not yet implemented")
    }

    override fun addTagsToPost(postId: Long, tags: Collection<TagId>): List<BaseTagModel> {
        TODO("Not yet implemented")
    }
}