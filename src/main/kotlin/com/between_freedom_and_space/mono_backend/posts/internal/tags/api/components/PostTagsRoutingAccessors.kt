package com.between_freedom_and_space.mono_backend.posts.internal.tags.api.components

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForAdmins
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForEveryone
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.grantAccessForUsers
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.routingAccessor
import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.ADMIN
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.SUPER_ADMIN
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InformationTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.exception.InvalidTagException
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import io.ktor.server.routing.*

@Suppress("DuplicatedCode")
internal fun Application.postTagsRoutingAccessors() {
    val basePath = "/tag"

    routing {

        grantAccessForEveryone("$basePath/byId/{id}")
        grantAccessForEveryone("$basePath/byAlias/{alias}")

        grantAccessForUsers("$basePath/create")

        grantAccessForAdmins("$basePath/all")

        val tagInformationService by inject<InformationTagsService>()

        routingAccessor("$basePath/byId/{id}/update",  ADMIN, SUPER_ADMIN) { userAccessData ->
            val userId = userAccessData.authority?.userId
            val tagId = userAccessData.request.getPathParameter("id")?.toLong()
                ?: throw InvalidTagException("Tag id is not presented")
            val tagAuthor = tagInformationService.getTagAuthorId(tagId)

            return@routingAccessor if (tagAuthor?.id == userId) {
                AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }

        routingAccessor("$basePath/byAlias/{alias}/update", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userId = userAccessData.authority?.userId
            val tagAlias = userAccessData.request.getPathParameter("alias")
                ?: throw InvalidTagException("Tag alias is not presented")
            val tagAuthor = tagInformationService.getTagAuthorId(tagAlias)

            return@routingAccessor if (tagAuthor?.id == userId) {
                AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }

        routingAccessor("$basePath/byId/{id}/delete", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userId = userAccessData.authority?.userId
            val tagId = userAccessData.request.getPathParameter("id")?.toLong()
                ?: throw InvalidTagException("Tag id is not presented")
            val tagAuthor = tagInformationService.getTagAuthorId(tagId)

            return@routingAccessor if (tagAuthor?.id == userId) {
                AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }

        routingAccessor("$basePath/byAlias/{alias}/delete", ADMIN, SUPER_ADMIN) { userAccessData ->
            val userId = userAccessData.authority?.userId
            val tagAlias = userAccessData.request.getPathParameter("alias")
                ?: throw InvalidTagException("Tag alias is not presented")
            val tagAuthor = tagInformationService.getTagAuthorId(tagAlias)

            return@routingAccessor if (tagAuthor?.id == userId) {
                AccessVerifyResult.ACCESSED
            } else {
                AccessVerifyResult.REJECTED
            }
        }
    }
}