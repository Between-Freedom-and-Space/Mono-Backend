package com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

@Suppress("DuplicatedCode")
class CommentReaction(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<CommentReaction>(CommentReactionsTable)

    var reaction by CommentReactionsTable.reaction

    var isDeleted by CommentReactionsTable.isDeleted

    var comment by CommentReactionsTable.comment

    var reactionBy by CommentReactionsTable.reactionBy

    var createdDate by CommentReactionsTable.createdDate

    var updatedDate by CommentReactionsTable.updatedDate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CommentReaction

        if (id.value != other.id.value) return false
        if (reaction != other.reaction) return false
        if (comment != other.comment) return false
        if (reactionBy != other.reactionBy) return false

        return true
    }

    override fun hashCode(): Int {
        var result = reaction.hashCode()
        result = 31 * result + id.value.hashCode()
        result = 31 * result + comment.hashCode()
        result = 31 * result + reactionBy.hashCode()
        return result
    }

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}