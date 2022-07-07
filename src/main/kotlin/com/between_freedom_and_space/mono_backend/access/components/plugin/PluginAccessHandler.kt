package com.between_freedom_and_space.mono_backend.access.components.plugin

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority
import io.ktor.server.request.*
import io.ktor.util.*

interface PluginAccessHandler {

    fun handleRequest(request: ApplicationRequest, attributes: Attributes)

    fun checkRoleAccess(authority: UserAuthority?, request: ApplicationRequest): AccessVerifyResult

    fun checkRuleToRoleAccess(authority: UserAuthority?, request: ApplicationRequest): AccessVerifyResult

    fun checkRuleToUserAccess(authority: UserAuthority?, request: ApplicationRequest): AccessVerifyResult
}