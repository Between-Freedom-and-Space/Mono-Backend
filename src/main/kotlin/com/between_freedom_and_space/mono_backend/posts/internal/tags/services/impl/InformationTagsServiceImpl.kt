package com.between_freedom_and_space.mono_backend.posts.internal.tags.services.impl

import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InformationTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.TagId

class InformationTagsServiceImpl: InformationTagsService {

    override fun getAllTags(pageNumber: Int, pageSize: Int): List<BaseTagModel> {
        TODO("Not yet implemented")
    }

    override fun getTagById(tagId: Long): BaseTagModel {
        TODO("Not yet implemented")
    }

    override fun getTagByAlias(tagAlias: String): BaseTagModel {
        TODO("Not yet implemented")
    }

    override fun getTags(ids: Collection<TagId>): List<BaseTagModel> {
        TODO("Not yet implemented")
    }

    override fun getTagsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<BaseTagModel> {
        TODO("Not yet implemented")
    }

    override fun getTagsWithPostId(postId: Long, pageNumber: Int, pageSize: Int): List<BaseTagModel> {
        TODO("Not yet implemented")
    }
}