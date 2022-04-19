package com.between_freedom_and_space.mono_backend.posts.entities.settings

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PostSettings(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<PostSettings>(PostsSettingsTable)

    var targetPost by PostsSettingsTable.targetPost

    var isVisibleForUnauthorizedUsers by PostsSettingsTable.isVisibleForUnauthorizedUsers

    var createdDate by PostsSettingsTable.createdDate

    var updatedDate by PostsSettingsTable.updatedDate
}