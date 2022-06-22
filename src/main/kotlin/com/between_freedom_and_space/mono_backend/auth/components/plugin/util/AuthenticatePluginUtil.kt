package com.between_freedom_and_space.mono_backend.auth.components.plugin.util

import com.between_freedom_and_space.mono_backend.auth.security.models.UserAuthority
import io.ktor.util.*
import kotlin.reflect.jvm.jvmName

val userAuthorityAttributeKey = AttributeKey<UserAuthority>(UserAuthority::class.jvmName)