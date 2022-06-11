package com.between_freedom_and_space.mono_backend.posts.entities.post

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostCommentsTable
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post.PostReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post.PostReactionsTable
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.tables.PostToTagTable
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Post(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<Post>(PostsTable)

    var name by PostsTable.name

    var text by PostsTable.text

    var author by PostsTable.author

    val comments by PostComment referrersOn PostCommentsTable.post

    val reactions by PostReaction referrersOn PostReactionsTable.post

    var tags by PostTag via PostToTagTable

    var isVisible by PostsTable.isVisible

    var isDeleted by PostsTable.isDeleted

    var isEdited by PostsTable.isEdited

    var createdDate by PostsTable.createdDate

    var updatedDate by PostsTable.updatedDate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id.value != other.id.value) return false
        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.value.hashCode()
        result = 31 * result + text.hashCode()
        return result
    }

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}
