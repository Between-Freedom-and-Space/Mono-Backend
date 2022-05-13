package com.between_freedom_and_space.mono_backend.posts.internal.comments.api.routing

import com.between_freedom_and_space.mono_backend.common.api.PageParams
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.CommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InformationCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.exceptions.InvalidCommentException
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*

internal fun Application.commentsInformationRouting() {
    val basePath = "/comment"

    val informationService by inject<InformationCommentsService>()
    val baseMapper by inject<ModelMapper<BaseCommentModel, CommentModel>>()

    routing {

        get("$basePath/all") {
            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageNumber = pageParams.pageNumber
            val pageSize = pageParams.pageSize

            val comments = informationService.getAllComments(pageNumber, pageSize)

            val commentsResponse = comments.map { baseMapper.map(it) }
            val response = Response.ok(commentsResponse)

            sendResponse(response)
        }

        get("$basePath/{id}") {
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidCommentException("Comment id is not presented")

            val comment = informationService.getCommentById(id)

            val commentResponse = baseMapper.map(comment)
            val response = Response.ok(commentResponse)

            sendResponse(response)
        }
    }
}