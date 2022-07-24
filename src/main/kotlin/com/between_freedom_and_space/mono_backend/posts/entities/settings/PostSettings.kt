package com.between_freedom_and_space.mono_backend.posts.entities.settings

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

class PostSettings(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<PostSettings>(PostsSettingsTable)

    var targetPost by PostsSettingsTable.targetPost

    var isVisibleForUnauthorizedUsers by PostsSettingsTable.isVisibleForUnauthorizedUsers

    var createdDate by PostsSettingsTable.createdDate

    var updatedDate by PostsSettingsTable.updatedDate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PostSettings

        if (id.value != other.id.value) return false
        if (targetPost != other.targetPost) return false
        if (isVisibleForUnauthorizedUsers != other.isVisibleForUnauthorizedUsers) return false

        return true
    }

    override fun hashCode(): Int {
        var result = targetPost.hashCode()
        result = 31 * result + id.value.hashCode()
        result = 31 * result + isVisibleForUnauthorizedUsers.hashCode()
        return result
    }

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}