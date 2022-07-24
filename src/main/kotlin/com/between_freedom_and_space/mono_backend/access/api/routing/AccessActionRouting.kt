package com.between_freedom_and_space.mono_backend.access.api.routing

import com.between_freedom_and_space.mono_backend.access.api.models.ChangeUserRoleRequest
import com.between_freedom_and_space.mono_backend.access.api.models.UserRoleModel
import com.between_freedom_and_space.mono_backend.access.modules.qualifiers.AccessMappersQualifiers
import com.between_freedom_and_space.mono_backend.access.service.ActionUserRolesService
import com.between_freedom_and_space.mono_backend.access.service.exception.InvalidRoleException
import com.between_freedom_and_space.mono_backend.access.service.models.BaseUserRoleModel
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.accessActionRouting() {
    val basePath = "/access/role"

    val actionService by inject<ActionUserRolesService>()
    val baseMapper by inject<ModelMapper<BaseUserRoleModel, UserRoleModel>>(
        named(AccessMappersQualifiers.BASE_USER_ROLE_TO_USER_ROLE_MODEL_MAPPER)
    )

    routing {

        post("$basePath/user/{nickname}/change") {
            val request = validateAndReceiveRequest<ChangeUserRoleRequest>()
            val newRole = request.newRole
            val nickname = getPathParameter("nickname")
                ?: throw InvalidRoleException("User nickname is not presented")

            val role = actionService.changeUserRole(nickname, newRole)

            val roleResponse = baseMapper.map(role)
            val response = Response.ok(roleResponse)

            sendResponse(response)
        }
    }
}