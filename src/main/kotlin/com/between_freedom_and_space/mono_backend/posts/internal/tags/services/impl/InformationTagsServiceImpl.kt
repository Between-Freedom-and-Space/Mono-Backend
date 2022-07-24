package com.between_freedom_and_space.mono_backend.posts.internal.tags.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.CommonTagsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InformationTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.exception.TagNotFoundException
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.TagAuthorId
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.TagId
import org.jetbrains.exposed.sql.transactions.transaction

class InformationTagsServiceImpl(
    private val tagRepository: CommonTagsRepository,
    private val baseMapper: ModelMapper<PostTag, BaseTagModel>,
): InformationTagsService {

    override fun getAllTags(pageNumber: Int, pageSize: Int): List<BaseTagModel> {
        val entities = transaction {
            tagRepository.getAllTags(pageNumber, pageSize)
        }
        return entities.map { baseMapper.map(it) }
    }

    override fun getTagById(tagId: Long): BaseTagModel {
        val entity = transaction {
            tagRepository.getTagById(tagId)
                ?: throw TagNotFoundException("Tag with id: $tagId not found")
        }
        return baseMapper.map(entity)
    }

    override fun getTagByAlias(tagAlias: String): BaseTagModel {
        val entity = transaction {
            tagRepository.getTagByAlias(tagAlias)
                ?: throw TagNotFoundException("Tag with alias: $tagAlias not found")
        }
        return baseMapper.map(entity)
    }

    override fun getTags(ids: Collection<TagId>): List<BaseTagModel> {
        val entities = transaction {
            tagRepository.getAllTagsWithIds(ids.map { it.value })
        }
        return entities.map { baseMapper.map(it) }
    }

    override fun getTagsWithAuthorId(authorId: Long, pageNumber: Int, pageSize: Int): List<BaseTagModel> {
        val entities = transaction {
            tagRepository.getTagsWithAuthorId(authorId, pageNumber, pageSize)
        }
        return entities.map { baseMapper.map(it) }
    }

    override fun getTagsWithPostId(postId: Long, pageNumber: Int, pageSize: Int): List<BaseTagModel> {
        val entities = transaction {
            tagRepository.getTagsWithPostId(postId, pageNumber, pageSize)
        }
        return entities.map { baseMapper.map(it) }
    }

    override fun getTagAuthorId(tagId: Long): TagAuthorId? {
        val authorId = transaction {
            val tag = tagRepository.getTagById(tagId)
                ?: throw TagNotFoundException("Tag with id: $tagId not found")
            tag.author?.value
        }
        return authorId?.let { TagAuthorId(it) }
    }

    override fun getTagAuthorId(tagAlias: String): TagAuthorId? {
        val authorId = transaction {
            val tag = tagRepository.getTagByAlias(tagAlias)
                ?: throw TagNotFoundException("Tag with alias: $tagAlias not found")
            tag.author?.value
        }
        return authorId?.let { TagAuthorId(it) }
    }
}