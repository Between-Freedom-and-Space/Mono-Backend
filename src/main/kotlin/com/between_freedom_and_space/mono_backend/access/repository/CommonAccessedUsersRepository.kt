package com.between_freedom_and_space.mono_backend.access.repository

import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule

interface CommonAccessedUsersRepository {

    fun getAllUserRules(nickname: String): List<AccessRule>

    fun getAllUserRules(userId: Long): List<AccessRule>
}