package com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PostReaction(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<PostReaction>(PostReactionsTable)

    var alias by PostReactionsTable.alias

    var reactionBase64 by PostReactionsTable.reactionBase64

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PostReaction

        if (id.value != other.id.value) return false
        if (alias != other.alias) return false
        if (reactionBase64 != other.reactionBase64) return false

        return true
    }

    override fun hashCode(): Int {
        var result = alias.hashCode()
        result = 31 * result + id.value.hashCode()
        result = 31 * result + reactionBase64.hashCode()
        return result
    }
}