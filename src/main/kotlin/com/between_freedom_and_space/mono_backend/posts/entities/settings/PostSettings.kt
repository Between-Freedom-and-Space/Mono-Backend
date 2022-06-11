package com.between_freedom_and_space.mono_backend.posts.entities.settings

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base.CallbackLongEntityClass
import com.between_freedom_and_space.mono_backend.util.support.localDateTimeNow
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PostSettings(id: EntityID<Long>): LongEntity(id) {
    companion object: CallbackLongEntityClass<PostSettings>(PostsSettingsTable)

    var targetPost by PostsSettingsTable.targetPost

    var isVisibleForUnauthorizedUsers by PostsSettingsTable.isVisibleForUnauthorizedUsers

    var createdDate by PostsSettingsTable.createdDate

    var updatedDate by PostsSettingsTable.updatedDate

    @PostUpdated
    fun postUpdated() {
        updatedDate = localDateTimeNow()
    }
}