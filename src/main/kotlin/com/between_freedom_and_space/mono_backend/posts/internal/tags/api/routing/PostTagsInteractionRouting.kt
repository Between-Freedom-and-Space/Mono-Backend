package com.between_freedom_and_space.mono_backend.posts.internal.tags.api.routing

import com.between_freedom_and_space.mono_backend.auth.components.plugin.extensions.getUserAuthorities
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.CreateTagRequest
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.TagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.UpdateTagRequest
import com.between_freedom_and_space.mono_backend.posts.internal.tags.modules.qualifiers.TagsMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InteractionTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.exception.InvalidTagException
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.CreateTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.UpdateTagModel
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

@Suppress("DuplicatedCode")
internal fun Application.postTagsInteractionRouting() {
    val basePath = "/tag"

    val interactionService by inject<InteractionTagsService>()

    val createMapper by inject<ModelMapper<CreateTagRequest, CreateTagModel>>(
        named(TagsMappersQualifiers.CREATE_TAG_REQUEST_TO_MODEL)
    )
    val updateMapper by inject<ModelMapper<UpdateTagRequest, UpdateTagModel>>(
        named(TagsMappersQualifiers.UPDATE_TAG_REQUEST_TO_MODEL)
    )
    val baseMapper by inject<ModelMapper<BaseTagModel, TagModel>>(
        named(TagsMappersQualifiers.BASE_TAG_MODEL_TO_MODEL)
    )

    routing {

        patch("$basePath/create") {
            val authority = getUserAuthorities()
            val authorId = authority.userId
            val request = validateAndReceiveRequest<CreateTagRequest>()
            val createModel = createMapper.map(request)

            val tag = interactionService.createTag(authorId, createModel)

            val tagResponse = baseMapper.map(tag)
            val response = Response.ok(tagResponse)

            sendResponse(response)
        }

        put("$basePath/byId/{id}/update") {
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidTagException("Tag id is not presented")
            val updateRequest = validateAndReceiveRequest<UpdateTagRequest>()
            val updateModel = updateMapper.map(updateRequest)

            val tag = interactionService.updateTag(id, updateModel)

            val tagResponse = baseMapper.map(tag)
            val response = Response.ok(tagResponse)

            sendResponse(response)
        }

        put("$basePath/byAlias/{alias}/update") {
            val alias = getPathParameter("alias")
                ?: throw InvalidTagException("Tag alias is not presented")
            val updateRequest = validateAndReceiveRequest<UpdateTagRequest>()
            val updateModel = updateMapper.map(updateRequest)

            val tag = interactionService.updateTag(alias, updateModel)

            val tagResponse = baseMapper.map(tag)
            val response = Response.ok(tagResponse)

            sendResponse(response)
        }

        delete("$basePath/byId/{id}/delete") {
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidTagException("Tag id is not presented")

            val tag = interactionService.deleteTag(id)

            val tagResponse = baseMapper.map(tag)
            val response = Response.ok(tagResponse)

            sendResponse(response)
        }

        delete("$basePath/byAlias/{alias}/delete") {
            val alias = getPathParameter("alias")
                ?: throw InvalidTagException("Tag alias is not presented")

            val tag = interactionService.deleteTag(alias)

            val tagResponse = baseMapper.map(tag)
            val response = Response.ok(tagResponse)

            sendResponse(response)
        }
    }
}