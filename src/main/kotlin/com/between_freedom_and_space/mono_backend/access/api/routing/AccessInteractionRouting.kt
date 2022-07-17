package com.between_freedom_and_space.mono_backend.access.api.routing

import com.between_freedom_and_space.mono_backend.access.api.models.*
import com.between_freedom_and_space.mono_backend.access.modules.qualifiers.AccessMappersQualifiers
import com.between_freedom_and_space.mono_backend.access.service.InteractionAccessRulesService
import com.between_freedom_and_space.mono_backend.access.service.exception.InvalidRuleException
import com.between_freedom_and_space.mono_backend.access.service.models.*
import com.between_freedom_and_space.mono_backend.auth.components.plugin.extensions.getUserAuthorities
import com.between_freedom_and_space.mono_backend.common.api.Response
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.util.extensions.getPathParameter
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import com.between_freedom_and_space.mono_backend.util.extensions.sendResponse
import com.between_freedom_and_space.mono_backend.util.extensions.validateAndReceiveRequest
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.core.qualifier.named

internal fun Application.accessInteractionRouting() {
    val basePath = "/access"

    val interactionService by inject<InteractionAccessRulesService>()

    val baseMapper by inject<ModelMapper<BaseAccessRuleModel, AccessRuleModel>>(
        named(AccessMappersQualifiers.BASE_ACCESS_RULE_TO_ACCESS_RULE_MAPPER)
    )
    val createRuleMapper by inject<ModelMapper<CreateRuleRequest, CreateRuleModel>>(
        named(AccessMappersQualifiers.CREATE_RULE_REQUEST_TO_MODEL_MAPPER)
    )
    val createUserRuleMapper by inject<ModelMapper<CreateUserRuleRequest, CreateUserRuleModel>>(
        named(AccessMappersQualifiers.CREATE_USER_RULE_REQUEST_TO_MODEL_MAPPER)
    )
    val createRoleRuleMapper by inject<ModelMapper<CreateRoleRuleRequest, CreateRoleRuleModel>>(
        named(AccessMappersQualifiers.CREATE_ROLE_RULE_REQUEST_TO_MODEL_MAPPER)
    )
    val updateRuleMapper by inject<ModelMapper<UpdateRuleRequest, UpdateRuleModel>>(
        named(AccessMappersQualifiers.UPDATE_RULE_REQUEST_TO_MODEL_MAPPER)
    )
    val updateUserRuleMapper by inject<ModelMapper<UpdateUserRuleRequest, UpdateUserRuleModel>>(
        named(AccessMappersQualifiers.UPDATE_USER_RULE_REQUEST_TO_MODEL_MAPPER)
    )
    val updateRoleRuleMapper by inject<ModelMapper<UpdateRoleRuleRequest, UpdateRoleRuleModel>>(
        named(AccessMappersQualifiers.UPDATE_ROLE_RULE_REQUEST_TO_MODEL_MAPPER)
    )

    routing {
        val ruleBasePath = "$basePath/rule"

        patch("$ruleBasePath/create") {
            val author = getUserAuthorities()
            val authorId = author.userId
            val request = validateAndReceiveRequest<CreateRuleRequest>()
            val model = createRuleMapper.map(request)

            val rule = interactionService.createAccessRule(authorId, model)

            val ruleResponse = baseMapper.map(rule)
            val response = Response.ok(ruleResponse)

            sendResponse(response)
        }

        patch("$ruleBasePath/user/create") {
            val author = getUserAuthorities()
            val authorId = author.userId
            val request = validateAndReceiveRequest<CreateUserRuleRequest>()
            val model = createUserRuleMapper.map(request)

            val rule = interactionService.createUserAccessRule(authorId, model)

            val ruleResponse = baseMapper.map(rule)
            val response = Response.ok(ruleResponse)

            sendResponse(response)
        }

        patch("$ruleBasePath/role/create") {
            val author = getUserAuthorities()
            val authorId = author.userId
            val request = validateAndReceiveRequest<CreateRoleRuleRequest>()
            val model = createRoleRuleMapper.map(request)

            val rule = interactionService.createRoleAccessRule(authorId, model)

            val ruleResponse = baseMapper.map(rule)
            val response = Response.ok(ruleResponse)

            sendResponse(response)
        }

        put("$ruleBasePath/{id}/update") {
            val author = getUserAuthorities()
            val authorId = author.userId
            val request = validateAndReceiveRequest<UpdateRuleRequest>()
            val model = updateRuleMapper.map(request)
            val ruleId = getPathParameter("id")?.toLong()
                ?: throw InvalidRuleException("Rule id is not provided")

            val rule = interactionService.updateAccessRule(ruleId, authorId, model)

            val ruleResponse = baseMapper.map(rule)
            val response = Response.ok(ruleResponse)

            sendResponse(response)
        }

        put("$ruleBasePath/user/{id}/update") {
            val author = getUserAuthorities()
            val authorId = author.userId
            val request = validateAndReceiveRequest<UpdateUserRuleRequest>()
            val model = updateUserRuleMapper.map(request)
            val ruleId = getPathParameter("id")?.toLong()
                ?: throw InvalidRuleException("Rule id is not provided")

            val rule = interactionService.updateUserAccessRule(ruleId, authorId, model)

            val ruleResponse = baseMapper.map(rule)
            val response = Response.ok(ruleResponse)

            sendResponse(response)
        }

        put("$ruleBasePath/role/{id}/update") {
            val author = getUserAuthorities()
            val authorId = author.userId
            val request = validateAndReceiveRequest<UpdateRoleRuleRequest>()
            val model = updateRoleRuleMapper.map(request)
            val ruleId = getPathParameter("id")?.toLong()
                ?: throw InvalidRuleException("Rule id is not provided")

            val rule = interactionService.updateRoleAccessRule(ruleId, authorId, model)

            val ruleResponse = baseMapper.map(rule)
            val response = Response.ok(ruleResponse)

            sendResponse(response)
        }

        delete("$ruleBasePath/{id}/delete") {
            val author = getUserAuthorities()
            val authorId = author.userId
            val ruleId = getPathParameter("id")?.toLong()
                ?: throw InvalidRuleException("Rule id is not provided")

            val rule = interactionService.deleteAccessRule(authorId, ruleId)

            val ruleResponse = baseMapper.map(rule)
            val response = Response.ok(ruleResponse)

            sendResponse(response)
        }

        delete("$ruleBasePath/user/{id}/delete") {
            val author = getUserAuthorities()
            val authorId = author.userId
            val ruleId = getPathParameter("id")?.toLong()
                ?: throw InvalidRuleException("Rule id is not provided")

            val rule = interactionService.deleteUserAccessRule(authorId, ruleId)

            val ruleResponse = baseMapper.map(rule)
            val response = Response.ok(ruleResponse)

            sendResponse(response)
        }

        delete("$ruleBasePath/role/{id}/delete") {
            val author = getUserAuthorities()
            val authorId = author.userId
            val ruleId = getPathParameter("id")?.toLong()
                ?: throw InvalidRuleException("Rule id is not provided")

            val rule = interactionService.deleteRoleAccessRule(authorId, ruleId)

            val ruleResponse = baseMapper.map(rule)
            val response = Response.ok(ruleResponse)

            sendResponse(response)
        }
    }
}