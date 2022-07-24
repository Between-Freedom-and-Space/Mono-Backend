package com.between_freedom_and_space.mono_backend.access.repository

import com.between_freedom_and_space.mono_backend.access.entities.role.models.UserToRole
import org.jetbrains.exposed.dao.id.EntityID

interface CommonUserToRoleRepository {

    fun getUserRole(userId: Long): UserToRole?

    fun getUserRole(nickname: String): UserToRole?

    fun createUserRole(userId: EntityID<Long>, roleId: EntityID<Long>): UserToRole

    fun save(userToRole: UserToRole): UserToRole
}