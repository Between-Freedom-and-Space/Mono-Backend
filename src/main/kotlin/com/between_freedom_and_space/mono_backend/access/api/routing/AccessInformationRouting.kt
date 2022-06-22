package com.between_freedom_and_space.mono_backend.access.api.routing

import com.between_freedom_and_space.mono_backend.access.api.models.UserRoleModel
import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.extensions.routingAccessor
import com.between_freedom_and_space.mono_backend.access.modules.qualifiers.AccessMappersQualifiers
import com.between_freedom_and_space.mono_backend.access.service.InformationUserRolesService
import com.between_freedom_and_space.mono_backend.access.service.exception.InvalidRoleException
import com.between_freedom_and_space.mono_backend.access.service.models.BaseUserRoleModel
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.accessInformationRouting() {
    val basePath = "/access"

    routing {
        val roleBasePath = "$basePath/role"

        val informationService by inject<InformationUserRolesService>()
        val baseMapper by inject<ModelMapper<BaseUserRoleModel, UserRoleModel>>(
            named(AccessMappersQualifiers.BASE_USER_ROLE_TO_USER_ROLE_MODEL_MAPPER)
        )

        get("$roleBasePath/all") {
            val roles = informationService.getAllRoles()

            val rolesResponse = roles.map { baseMapper.map(it) }
            val response = Response.ok(rolesResponse)

            sendResponse(response)
        }

        get("$roleBasePath/{id}") {
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidRoleException("Role id is not presented")

            val role = informationService.getRoleById(id)

            val roleResponse = baseMapper.map(role)
            val response = Response.ok(roleResponse)

            sendResponse(response)
        }

        get("$roleBasePath/user/{nickname}") {
            val nickname = getPathParameter("nickname")
                ?: throw InvalidRoleException("User nickname us not presented")

            val role = informationService.getUserRole(nickname)

            val roleResponse = baseMapper.map(role)
            val response = Response.ok(roleResponse)

            sendResponse(response)
        }
    }

    routing {
        val ruleBasePath = "$basePath/rule"

        get("$ruleBasePath/all") {

        }

        get("$ruleBasePath/{id}") {

        }
    }
}

