package com.between_freedom_and_space.mono_backend.common.api

import com.between_freedom_and_space.mono_backend.common.api.Error.Companion.DEFAULT_MESSAGE
import com.between_freedom_and_space.mono_backend.common.api.Error.Companion.NOT_PRESENTED_ERROR_ID
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response <T: Any>(

    @SerialName("status_code")
    val statusCode: Int,

    @SerialName("status_message")
    val statusMessage: String,

    @SerialName("content")
    val content: T?,

    @SerialName("error")
    val error: Error?,
) {

    companion object {

        fun <T: Any> ok(content: T?): Response<T> {
            return Response(
                statusCode = HttpStatusCode.OK.value,
                statusMessage = HttpStatusCode.OK.description,
                content = content,
                error = null,
            )
        }

        fun <T: Any> ok(content: () -> T?): Response<T> {
            return ok(content.invoke())
        }

        fun accepted(): Response<Unit> {
            return Response(
                statusCode = HttpStatusCode.Accepted.value,
                statusMessage = HttpStatusCode.Accepted.description,
                content = null,
                error = null,
            )
        }

        fun badRequest(errorId: Long? = null, message: String? = null): Response<Unit> {
            val error = Error(
                errorId = errorId ?: NOT_PRESENTED_ERROR_ID,
                message = message ?: DEFAULT_MESSAGE
            )
            return with(error, HttpStatusCode.BadRequest)
        }

        fun unauthorized(errorId: Long? = null, message: String? = null): Response<Unit> {
            val error = Error(
                errorId = errorId ?: NOT_PRESENTED_ERROR_ID,
                message = message ?: DEFAULT_MESSAGE
            )
            return with(error, HttpStatusCode.Unauthorized)
        }

        fun forbidden(errorId: Long? = null, message: String? = null): Response<Unit> {
            val error = Error(
                errorId = errorId ?: NOT_PRESENTED_ERROR_ID,
                message = message ?: DEFAULT_MESSAGE
            )
            return with(error, HttpStatusCode.Forbidden)
        }

        fun notFound(errorId: Long? = null, message: String? = null): Response<Unit> {
            val error = Error(
                errorId = errorId ?: NOT_PRESENTED_ERROR_ID,
                message = message ?: DEFAULT_MESSAGE
            )
            return with(error, HttpStatusCode.NotFound)
        }

        fun internalServerError(errorId: Long? = null, message: String? = null): Response<Unit> {
            val error = Error(
                errorId = errorId ?: NOT_PRESENTED_ERROR_ID,
                message = message ?: DEFAULT_MESSAGE
            )
            return with(error, HttpStatusCode.InternalServerError)
        }

        fun with(error: Error, httpStatus: HttpStatusCode): Response<Unit> {
            return Response(
                statusCode = httpStatus.value,
                statusMessage = httpStatus.description,
                content = Unit,
                error = error,
            )
        }
    }
}
