package com.between_freedom_and_space.mono_backend.posts.internal.tags.api.routing

import com.between_freedom_and_space.mono_backend.common.api.PageParams
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.TagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InformationTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.exception.InvalidTagException
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.postTagsInformationRouting() {
    val basePath = "/tag"

    val informationService by inject<InformationTagsService>()
    val baseMapper by inject<ModelMapper<BaseTagModel, TagModel>>()

    routing {

        get("$basePath/all") {
            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageNumber = pageParams.pageNumber
            val pageSize = pageParams.pageSize

            val tags = informationService.getAllTags(pageNumber, pageSize)

            val tagsResponse = tags.map { baseMapper.map(it) }
            val response = Response.ok(tagsResponse)

            sendResponse(response)
        }

        get("$basePath/byId/{id}") {
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidTagException("Tag id is not presented")

            val tag = informationService.getTagById(id)

            val tagResponse = baseMapper.map(tag)
            val response = Response.ok(tagResponse)

            sendResponse(response)
        }

        get("$basePath/byAlias/{alias}") {
            val alias = getPathParameter("alias")
                ?: throw InvalidTagException("Tag alias is not presented")

            val tag = informationService.getTagByAlias(alias)

            val tagResponse = baseMapper.map(tag)
            val response = Response.ok(tagResponse)

            sendResponse(response)
        }
    }
}