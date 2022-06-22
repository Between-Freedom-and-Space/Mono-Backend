package com.between_freedom_and_space.mono_backend.access.repository

import com.between_freedom_and_space.mono_backend.access.entities.role.models.UserToRole

interface CommonUserToRoleRepository {

    fun getUserRole(userId: Long): UserToRole?

    fun getUserRole(nickname: String): UserToRole?
}