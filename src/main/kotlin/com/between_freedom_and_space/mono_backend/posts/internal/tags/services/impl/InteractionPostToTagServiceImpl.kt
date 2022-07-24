package com.between_freedom_and_space.mono_backend.posts.internal.tags.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.CommonPostToTagRepository
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.CommonTagsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InteractionPostToTagService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.TagId
import com.between_freedom_and_space.mono_backend.posts.repository.CommonPostRepository
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.PostNotFoundException
import org.jetbrains.exposed.sql.transactions.transaction

class InteractionPostToTagServiceImpl(
    private val postToTagRepository: CommonPostToTagRepository,
    private val postRepository: CommonPostRepository,
    private val tagsRepository: CommonTagsRepository,
    private val entityMapper: ModelMapper<PostTag, BaseTagModel>,
): InteractionPostToTagService {

    override fun deleteAllPostTags(postId: Long): List<BaseTagModel> {
        val entities = transaction {
            val post = postRepository.getPostById(postId)
                ?: throw PostNotFoundException("Post with id: $postId not found")
            val deletedTags = postToTagRepository.deleteTagsFromPost(post.id)
            val deletedTagsIds = deletedTags.map { it.tag.value }
            tagsRepository.getAllTagsWithIds(deletedTagsIds)
        }

        return entities.map { entityMapper.map(it) }
    }

    override fun addTagsToPost(postId: Long, tags: Collection<TagId>): List<BaseTagModel> {
        val entities = transaction {
            val post = postRepository.getPostById(postId)
                ?: throw PostNotFoundException("Post with id: $postId not found")
            val tagsEntities = tagsRepository
                .getAllTagsWithIds(tags.map { it.value })
                .map { it.id }
            val addedTags = postToTagRepository.addTagsToPost(post.id, tagsEntities)
            val addedTagsIds = addedTags.map { it.tag.value }
            tagsRepository.getAllTagsWithIds(addedTagsIds)
        }

        return entities.map { entityMapper.map(it) }
    }
}