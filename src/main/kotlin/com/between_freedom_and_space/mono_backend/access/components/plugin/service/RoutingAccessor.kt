package com.between_freedom_and_space.mono_backend.access.components.plugin.service

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult
import com.between_freedom_and_space.mono_backend.access.components.plugin.models.UserAccessData
import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority
import io.ktor.server.request.*

typealias RoutingAccessor = (UserAccessData) -> AccessVerifyResult