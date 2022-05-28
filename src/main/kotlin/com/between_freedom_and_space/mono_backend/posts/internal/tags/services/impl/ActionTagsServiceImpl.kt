package com.between_freedom_and_space.mono_backend.posts.internal.tags.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.CommonTagsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.ActionTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.exception.TagNotFoundException
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.mappers.TagEntityToBaseModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.CreateTagModel
import org.h2.engine.Mode
import org.jetbrains.exposed.sql.transactions.transaction

class ActionTagsServiceImpl(
    private val tagsRepository: CommonTagsRepository,
    private val entityMapper: ModelMapper<PostTag, BaseTagModel>
): ActionTagsService {

    override fun getOrCreateTagsWithAliases(aliases: Collection<String>): List<BaseTagModel> {
        val entities = transaction {
            val tags = mutableListOf<PostTag>()

            aliases.forEach { alias ->
                val tag = if (tagsRepository.tagExists(alias)) {
                    getTagOrThrow(alias)
                } else {
                    createTag(alias)
                }
                tags.add(tag)
            }

            tags
        }

        return entities.map { entityMapper.map(it) }
    }

    private fun getTagOrThrow(tagAlias: String): PostTag {
        return tagsRepository.getTagByAlias(tagAlias)
            ?: throw TagNotFoundException("Tag with alias: $tagAlias not found")
    }

    private fun createTag(alias: String): PostTag {
        val createModel = CreateTagModel(
            tagAlias = alias,
            tagDescription = null,
        )
        return tagsRepository.createTag(createModel)
    }
}