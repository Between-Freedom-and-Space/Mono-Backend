package com.between_freedom_and_space.mono_backend.access.repository.impl

import com.between_freedom_and_space.mono_backend.access.entities.role.models.UserToRole
import com.between_freedom_and_space.mono_backend.access.entities.role.tables.UserToRolesTable
import com.between_freedom_and_space.mono_backend.access.repository.CommonUserToRoleRepository
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserProfilesTable
import org.jetbrains.exposed.dao.id.EntityID

class CommonUserToRoleRepositoryImpl: CommonUserToRoleRepository {

    override fun getUserRole(userId: Long): UserToRole? {
        return UserToRole.find {
            UserToRolesTable.user eq userId
        }.firstOrNull()
    }

    override fun getUserRole(nickname: String): UserToRole? {
        val user = UserProfile.find {
            UserProfilesTable.nickName eq nickname
        }.firstOrNull()
        val userId = user?.id?.value
        return userId?.let { id ->
            UserToRole.find {
                UserToRolesTable.user eq id
            }.firstOrNull()
        }
    }

    override fun createUserRole(userId: EntityID<Long>, roleId: EntityID<Long>): UserToRole {
        return UserToRole.new {
            role = roleId
            user = userId
        }
    }

    override fun save(userToRole: UserToRole): UserToRole {
        userToRole.flush()
        return userToRole
    }
}