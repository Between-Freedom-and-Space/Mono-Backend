package com.between_freedom_and_space.mono_backend.access.components.plugin.service.impl

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult.ACCESSED
import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult.REJECTED
import com.between_freedom_and_space.mono_backend.access.components.plugin.models.UserAccessData
import com.between_freedom_and_space.mono_backend.access.components.plugin.service.RoutingAccessor
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.ADMIN
import com.between_freedom_and_space.mono_backend.access.entities.role.Role.SUPER_ADMIN

class DefaultRoutingAccessor: RoutingAccessor {

    override fun invoke(userAccessData: UserAccessData): AccessVerifyResult {
        return when (userAccessData.role) {
            ADMIN -> ACCESSED
            SUPER_ADMIN -> ACCESSED
            else -> REJECTED
        }
    }
}