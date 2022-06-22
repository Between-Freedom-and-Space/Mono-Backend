package com.between_freedom_and_space.mono_backend.access.repository.impl

import com.between_freedom_and_space.mono_backend.access.entities.role.models.UserToRole
import com.between_freedom_and_space.mono_backend.access.entities.role.tables.UserToRolesTable
import com.between_freedom_and_space.mono_backend.access.repository.CommonUserToRoleRepository
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.entities.tables.UserProfilesTable

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
}