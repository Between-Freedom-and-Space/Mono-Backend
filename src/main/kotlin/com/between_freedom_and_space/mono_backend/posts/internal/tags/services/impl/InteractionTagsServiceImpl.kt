package com.between_freedom_and_space.mono_backend.posts.internal.tags.services.impl

import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InteractionTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.CreateTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.UpdateTagModel

class InteractionTagsServiceImpl: InteractionTagsService {

    override fun createTag(authorId: Long, model: CreateTagModel): BaseTagModel {
        TODO("Not yet implemented")
    }

    override fun updateTag(tagId: Long, model: UpdateTagModel): BaseTagModel {
        TODO("Not yet implemented")
    }

    override fun updateTag(tagAlias: String, model: UpdateTagModel): BaseTagModel {
        TODO("Not yet implemented")
    }

    override fun deleteTag(tagId: Long): BaseTagModel {
        TODO("Not yet implemented")
    }

    override fun deleteTag(tagAlias: String): BaseTagModel {
        TODO("Not yet implemented")
    }
}