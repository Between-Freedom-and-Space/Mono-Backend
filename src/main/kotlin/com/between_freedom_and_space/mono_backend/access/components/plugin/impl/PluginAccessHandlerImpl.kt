package com.between_freedom_and_space.mono_backend.access.components.plugin.impl

import com.between_freedom_and_space.mono_backend.access.components.exceptions.AccessException
import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.PluginAccessHandler
import com.between_freedom_and_space.mono_backend.access.components.plugin.models.UserAccessData
import com.between_freedom_and_space.mono_backend.access.components.plugin.util.roleAttributeKey
import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.service.InformationAccessRulesService
import com.between_freedom_and_space.mono_backend.access.service.InformationUserRolesService
import com.between_freedom_and_space.mono_backend.access.service.models.RuleCheckResult
import com.between_freedom_and_space.mono_backend.access.service.models.RuleCheckResult.CheckResult
import com.between_freedom_and_space.mono_backend.auth.components.plugin.util.userAuthorityAttributeKey
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority
import io.ktor.server.request.*
import io.ktor.util.*

class PluginAccessHandlerImpl(
    private val roleInformationService: InformationUserRolesService,
    private val accessRuleInformationService: InformationAccessRulesService,
): PluginAccessHandler {

    override fun handleRequest(request: ApplicationRequest, attributes: Attributes) {
        val authority = attributes.getOrNull(userAuthorityAttributeKey)

        if (authority == null) {
            attributes.put(roleAttributeKey, Role.NO_ROLE)
            return
        } else {
            attributes.put(roleAttributeKey, Role.DEFAULT_USER)
        }

        val role = roleInformationService.getUserRoleOrNull(authority.userId)
        role?.let { attributes.put(roleAttributeKey, it.role) }
    }

    override fun checkRoleAccess(userAccessData: UserAccessData): AccessVerifyResult {
        val role = userAccessData.role
        if (role == Role.SUPER_ADMIN) {
            return AccessVerifyResult.ACCESSED
        }
        return AccessVerifyResult.REJECTED
    }

    override fun checkRuleToRoleAccess(userAccessData: UserAccessData): AccessVerifyResult {
        val role = userAccessData.role
        if (role == Role.NO_ROLE) {
            return AccessVerifyResult.REJECTED
        }

        val rawPath = userAccessData.request.path()
        val result = accessRuleInformationService.checkRoleAccessToPath(role, rawPath)
        val checkResult = result.checkResult

        return if (checkResult == CheckResult.HAS_ACCESS) {
            AccessVerifyResult.ACCESSED
        } else {
            AccessVerifyResult.REJECTED
        }
    }

    override fun checkRuleToUserAccess(userAccessData: UserAccessData): AccessVerifyResult {
        val role = userAccessData.role
        if (role == Role.NO_ROLE) {
            return AccessVerifyResult.REJECTED
        }

        val rawPath = userAccessData.request.path()
        val userId = userAccessData.authority?.userId
            ?: throw AccessException("Access denied: no enough information about user")
        val result = accessRuleInformationService.checkUserAccessToPath(userId, rawPath)
        val checkResult = result.checkResult

        return if (checkResult == CheckResult.HAS_ACCESS) {
            AccessVerifyResult.ACCESSED
        } else {
            AccessVerifyResult.REJECTED
        }
    }
}