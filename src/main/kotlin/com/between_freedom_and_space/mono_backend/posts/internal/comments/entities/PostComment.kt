package com.between_freedom_and_space.mono_backend.posts.internal.comments.entities

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

class PostComment(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<PostComment>(PostCommentsTable)

    var post by PostCommentsTable.post

    var text by PostCommentsTable.text

    var isDeleted by PostCommentsTable.isDeleted

    var author by PostCommentsTable.author

    var createdDate by PostCommentsTable.createdDate

    var updatedDate by PostCommentsTable.updatedDate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PostComment

        if (id.value != other.id.value) return false
        if (text != other.text) return false
        if (author != other.author) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.value.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + author.hashCode()
        return result
    }

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}