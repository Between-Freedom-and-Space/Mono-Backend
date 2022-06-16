package com.between_freedom_and_space.mono_backend.posts.internal.tags.services

import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import org.jetbrains.exposed.dao.id.EntityID

interface ActionTagsService {

    fun getOrCreateTagsWithAliases(aliases: Collection<String>, authorId: EntityID<Long>? = null): List<BaseTagModel>
}