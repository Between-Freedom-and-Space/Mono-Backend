package com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.impl

import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostToTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.tables.PostTagsTable
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.tables.PostToTagTable
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.CommonPostToTagRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select

class CommonPostToTagRepositoryImpl: CommonPostToTagRepository {

    override fun addTagToPost(postId: EntityID<Long>, tagId: EntityID<Long>): PostToTag {
        return PostToTag.new {
            post = postId
            tag = tagId
        }
    }

    override fun addTagsToPost(postId: EntityID<Long>, tagsIds: Collection<EntityID<Long>>): List<PostToTag> {
        val result = mutableListOf<PostToTag>()

        tagsIds.forEach { tagId ->
            val postToRag = PostToTag.new {
                post = postId
                tag = tagId
            }
            result.add(postToRag)
        }

        return result
    }

    override fun deleteTagsFromPost(postId: EntityID<Long>): List<PostToTag> {
        val query = PostToTagTable
            .select {
                PostToTagTable.post eq postId
            }
        val result = PostToTag.wrapRows(query)
        return result.toList().onEach { it.delete() }
    }
}