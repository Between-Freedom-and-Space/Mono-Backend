package com.between_freedom_and_space.mono_backend.posts.internal.tags.repository

import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostToTag
import org.jetbrains.exposed.dao.id.EntityID

interface CommonPostToTagRepository {

    fun addTagToPost(postId: EntityID<Long>, tagId: EntityID<Long>): PostToTag

    fun addTagsToPost(postId: EntityID<Long>, tagsIds: Collection<EntityID<Long>>): List<PostToTag>

    fun deleteTagsFromPost(postId: EntityID<Long>): List<PostToTag>
}