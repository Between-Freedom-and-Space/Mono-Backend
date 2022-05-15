package com.between_freedom_and_space.mono_backend.posts.internal.tags.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.CommonTagsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InteractionTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.exception.TagNotFoundException
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.CreateTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.UpdateTagModel
import org.jetbrains.exposed.sql.transactions.transaction

class InteractionTagsServiceImpl(
    private val tagRepository: CommonTagsRepository,
    private val entityMapper: ModelMapper<PostTag, BaseTagModel>,
): InteractionTagsService {

    override fun createTag(authorId: Long, model: CreateTagModel): BaseTagModel {
        val entity = transaction {
            tagRepository.createTag(authorId, model)
        }
        return entityMapper.map(entity)
    }

    override fun updateTag(tagId: Long, model: UpdateTagModel): BaseTagModel {
        val entity = transaction {
            val tag = tagRepository.getTagById(tagId)
                ?: throw TagNotFoundException("Tag with id: $tagId not found")

            updateTag(tag, model)
            tagRepository.saveTag(tag)
        }
        return entityMapper.map(entity)
    }

    override fun updateTag(tagAlias: String, model: UpdateTagModel): BaseTagModel {
        val entity = transaction {
            val tag = tagRepository.getTagByAlias(tagAlias)
                ?: throw TagNotFoundException("Tag with alias: $tagAlias not found")

            updateTag(tag, model)
            tagRepository.saveTag(tag)
        }
        return entityMapper.map(entity)
    }

    override fun deleteTag(tagId: Long): BaseTagModel {
        val entity = transaction {
            tagRepository.deleteTag(tagId)
                ?: throw TagNotFoundException("Tag with id: $tagId not found")
        }
        return entityMapper.map(entity)
    }

    override fun deleteTag(tagAlias: String): BaseTagModel {
        val entity = transaction {
            tagRepository.deleteTag(tagAlias)
                ?: throw TagNotFoundException("Tag with alias: $tagAlias not found")
        }
        return entityMapper.map(entity)
    }

    private fun updateTag(tag: PostTag, updateModel: UpdateTagModel) {
        updateModel.newAlias?.let { tag.tagAlias = it }
        updateModel.newDescription?.let { tag.tagDescription = it }
    }
}