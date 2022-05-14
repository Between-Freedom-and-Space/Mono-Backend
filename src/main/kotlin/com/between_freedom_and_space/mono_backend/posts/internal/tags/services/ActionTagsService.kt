package com.between_freedom_and_space.mono_backend.posts.internal.tags.services

import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel

interface ActionTagsService {

    fun getOrCreateTagsWithAliases(aliases: Collection<String>): List<BaseTagModel>
}