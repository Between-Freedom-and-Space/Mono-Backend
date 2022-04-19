package com.between_freedom_and_space.mono_backend.posts.internal.comments.entities

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PostComment(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<PostComment>(PostCommentsTable)

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

        return true
    }

    override fun hashCode(): Int {
        var result = id.value.hashCode()
        result = 31 * result + text.hashCode()
        return result
    }
}