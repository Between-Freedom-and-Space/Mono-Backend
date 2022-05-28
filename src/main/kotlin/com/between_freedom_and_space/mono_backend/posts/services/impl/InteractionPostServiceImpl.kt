package com.between_freedom_and_space.mono_backend.posts.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.CommonTagsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.ActionTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InteractionPostToTagService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.TagId
import com.between_freedom_and_space.mono_backend.posts.repository.CommonPostRepository
import com.between_freedom_and_space.mono_backend.posts.repository.models.CreatePostEntityModel
import com.between_freedom_and_space.mono_backend.posts.services.InteractionPostsService
import com.between_freedom_and_space.mono_backend.posts.services.exceptions.PostNotFoundException
import com.between_freedom_and_space.mono_backend.posts.services.mappers.PostEntityToBasePostModelMapper
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.CreatePostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.UpdatePostModel
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.ProfileNotFoundException
import org.jetbrains.exposed.sql.transactions.transaction

class InteractionPostServiceImpl(
    private val postRepository: CommonPostRepository,
    private val interactionPostToTagService: InteractionPostToTagService,
    private val actionTagsService: ActionTagsService,
    private val profilesRepository: CommonProfilesRepository,
    private val tagsRepository: CommonTagsRepository,
    private val entityMapper: ModelMapper<Post, BasePostModel>,
    private val createEntityMapper: ModelMapper<CreatePostModel, CreatePostEntityModel>
): InteractionPostsService {

    override fun createPost(authorId: Long, createPostModel: CreatePostModel): BasePostModel {
        return transaction {
            val profile = profilesRepository.getProfileById(authorId)
                ?: throw ProfileNotFoundException("Profile with id: $authorId not found")
            val tagModels = actionTagsService.getOrCreateTagsWithAliases(createPostModel.tagsAliases)
            val tagsEntities = tagsRepository.getAllTagsWithIds(tagModels.map { it.id })

            val createPost = createEntityMapper.map(createPostModel)
            val entity = postRepository.createPost(profile.id, tagsEntities, createPost)
            entityMapper.map(entity)
        }
    }

    override fun updatePost(postId: Long, updateModel: UpdatePostModel): BasePostModel {
        val entity = transaction {
            val post = postRepository.getPostById(postId)
                ?: throw PostNotFoundException("Post with id: $postId not found")

            updateModel.newName?.let { post.name = it }
            updateModel.newText?.let { post.text = it }
            updateModel.newVisibility?.let { post.isVisible = it }
            updateModel.newTagsAliases?.let { updatePostTags(post, it) }

            postRepository.savePost(post)
        }

        return entityMapper.map(entity)
    }

    override fun deletePost(postId: Long): BasePostModel {
        val entity = transaction {
            postRepository.deletePost(postId)
                ?: throw PostNotFoundException("Post with id: $postId not found")
        }
        return entityMapper.map(entity)
    }

    private fun updatePostTags(post: Post, tagAliases: Collection<String>) {
        val postId = post.id.value
        interactionPostToTagService.deleteAllPostTags(postId)
        val tagModels = actionTagsService.getOrCreateTagsWithAliases(tagAliases)
        val tagIds = tagModels.map { TagId(it.id) }
        interactionPostToTagService.addTagsToPost(postId, tagIds)
    }
}