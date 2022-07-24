package com.between_freedom_and_space.mono_backend.access.components.plugin.util

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import io.ktor.util.*
import kotlin.reflect.jvm.jvmName

val roleAttributeKey = AttributeKey<Role>(Role::class.jvmName)