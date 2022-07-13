package com.between_freedom_and_space.mono_backend.access.api.routing

import com.between_freedom_and_space.mono_backend.access.api.models.AccessRuleModel
import com.between_freedom_and_space.mono_backend.access.api.models.HasAccessResponse
import com.between_freedom_and_space.mono_backend.access.api.models.HasAccessRequest
import com.between_freedom_and_space.mono_backend.access.api.models.UserRoleModel
import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.modules.qualifiers.AccessMappersQualifiers
import com.between_freedom_and_space.mono_backend.access.service.InformationAccessRulesService
import com.between_freedom_and_space.mono_backend.access.service.InformationUserRolesService
import com.between_freedom_and_space.mono_backend.access.service.exception.InvalidRoleException
import com.between_freedom_and_space.mono_backend.access.service.exception.InvalidRuleException
import com.between_freedom_and_space.mono_backend.access.service.models.BaseAccessRuleModel
import com.between_freedom_and_space.mono_backend.access.service.models.BaseUserRoleModel
import com.between_freedom_and_space.mono_backend.access.service.models.RuleCheckResult
import com.between_freedom_and_space.mono_backend.common.api.PageParams
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
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

        val informationService by inject<InformationAccessRulesService>()
        val baseMapper by inject<ModelMapper<BaseAccessRuleModel, AccessRuleModel>>(
            named(AccessMappersQualifiers.BASE_ACCESS_RULE_TO_ACCESS_RULE_MAPPER)
        )
        val checkResultMapper by inject<ModelMapper<RuleCheckResult, HasAccessResponse>>(
            named(AccessMappersQualifiers.CHECK_ACCESS_RESULT_TO_RESPONSE_MAPPER)
        )

        get("$ruleBasePath/all") {
            val pageParams = validateAndReceiveRequest<PageParams>()
            val pageNumber = pageParams.pageNumber
            val pageSize = pageParams.pageSize

            val rules = informationService.getAllAccessRules(pageNumber, pageSize)

            val rulesResponse = rules.map { baseMapper.map(it) }
            val response = Response.ok(rulesResponse)

            sendResponse(response)
        }

        get("$ruleBasePath/{id}") {
            val id = getPathParameter("id")?.toLong()
                ?: throw InvalidRuleException("Rule id not provided")

            val rule = informationService.getAccessRuleById(id)

            val ruleResponse = baseMapper.map(rule)
            val response = Response.ok(ruleResponse)

            sendResponse(response)
        }

        get("$ruleBasePath/user/{nickname}") {
            val nickname = getPathParameter("nickname")
                ?: throw InvalidRuleException("Nickname not provided")

            val rules = informationService.getAllUserRules(nickname)

            val rulesResponse = rules.map { baseMapper.map(it) }
            val response = Response.ok(rulesResponse)

            sendResponse(response)
        }

        get("$ruleBasePath/role/{roleAlias}") {
            val alias = getPathParameter("roleAlias")
                ?: throw InvalidRuleException("Role alias not provided")

            val rules = informationService.getAllRoleRules(alias)

            val rulesResponse = rules.map { baseMapper.map(it) }
            val response = Response.ok(rulesResponse)

            sendResponse(response)
        }

        get("$ruleBasePath/user/{nickname}/has/access") {
            val nickname = getPathParameter("nickname")
                ?: throw InvalidRuleException("Nickname not provided")
            val hasAccessRequest = validateAndReceiveRequest<HasAccessRequest>()
            val rawPath = hasAccessRequest.rawPath

            val checkResult = informationService.checkUserAccessToPath(nickname, rawPath)

            val checkResponse = checkResultMapper.map(checkResult)
            val response = Response.ok(checkResponse)

            sendResponse(response)
        }

        get("$ruleBasePath/role/{alias}/has/access") {
            val alias = getPathParameter("alias")
                ?: throw InvalidRuleException("Role alias not provided")
            val role = Role.valueOf(alias)
            val hasAccessRequest = validateAndReceiveRequest<HasAccessRequest>()
            val rawPath = hasAccessRequest.rawPath

            val checkResult = informationService.checkRoleAccessToPath(role, rawPath)

            val checkResponse = checkResultMapper.map(checkResult)
            val response = Response.ok(checkResponse)

            sendResponse(response)
        }
    }
}

