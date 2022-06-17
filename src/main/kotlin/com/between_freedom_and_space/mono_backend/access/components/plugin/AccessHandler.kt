package com.between_freedom_and_space.mono_backend.access.components.plugin

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import io.ktor.server.request.*
import io.ktor.util.*

interface AccessHandler {

    fun handleRequest(request: ApplicationRequest, attributes: Attributes)

    fun checkRuleToRoleAccess(): AccessVerifyResult

    fun checkRuleToUserAccess(): AccessVerifyResult
}