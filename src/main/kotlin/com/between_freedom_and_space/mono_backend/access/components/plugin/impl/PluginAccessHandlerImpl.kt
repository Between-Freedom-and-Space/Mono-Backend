package com.between_freedom_and_space.mono_backend.access.components.plugin.impl

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.PluginAccessHandler
import com.between_freedom_and_space.mono_backend.access.components.plugin.util.roleAttributeKey
import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.service.InformationUserRolesService
import com.between_freedom_and_space.mono_backend.auth.components.plugin.util.userAuthorityAttributeKey
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority
import io.ktor.server.request.*
import io.ktor.util.*

class PluginAccessHandlerImpl(
    private val roleInformationService: InformationUserRolesService
): PluginAccessHandler {

    override fun handleRequest(request: ApplicationRequest, attributes: Attributes) {
        val authority = attributes.getOrNull(userAuthorityAttributeKey)

        if (authority == null) {
            attributes.put(roleAttributeKey, Role.NO_ROLE)
            return
        }

        val role = roleInformationService.getUserRole(authority.userId)
        attributes.put(roleAttributeKey, role.role)
    }

    override fun checkRoleAccess(authority: UserAuthority?, request: ApplicationRequest): AccessVerifyResult {
        TODO("Not yet implemented")
    }

    override fun checkRuleToRoleAccess(authority: UserAuthority?, request: ApplicationRequest): AccessVerifyResult {
        TODO("Not yet implemented")
    }

    override fun checkRuleToUserAccess(authority: UserAuthority?, request: ApplicationRequest): AccessVerifyResult {
        TODO("Not yet implemented")
    }
}