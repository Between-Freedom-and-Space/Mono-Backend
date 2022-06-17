package com.between_freedom_and_space.mono_backend.access.components.plugin.impl

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.AccessHandler
import io.ktor.server.request.*
import io.ktor.util.*

class AccessHandlerImpl: AccessHandler {

    override fun handleRequest(request: ApplicationRequest, attributes: Attributes) {
        TODO("Not yet implemented")
    }

    override fun checkRuleToRoleAccess(): AccessVerifyResult {
        TODO("Not yet implemented")
    }

    override fun checkRuleToUserAccess(): AccessVerifyResult {
        TODO("Not yet implemented")
    }
}