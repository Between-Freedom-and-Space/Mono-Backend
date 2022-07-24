package com.between_freedom_and_space.mono_backend.access.service.impl

import com.between_freedom_and_space.mono_backend.access.entities.role.Role
import com.between_freedom_and_space.mono_backend.access.entities.role.models.UserRole
import com.between_freedom_and_space.mono_backend.access.entities.role.models.UserToRole
import com.between_freedom_and_space.mono_backend.access.repository.CommonUserRoleRepository
import com.between_freedom_and_space.mono_backend.access.repository.CommonUserToRoleRepository
import com.between_freedom_and_space.mono_backend.access.service.ActionUserRolesService
import com.between_freedom_and_space.mono_backend.access.service.exception.RoleNotFoundException
import com.between_freedom_and_space.mono_backend.access.service.models.BaseUserRoleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.profiles.components.UserProfileIdProvider
import org.jetbrains.exposed.sql.transactions.transaction

class ActionUserRolesServiceImpl(
    private val roleRepository: CommonUserRoleRepository,
    private val userToRoleRepository: CommonUserToRoleRepository,
    private val userIdProvider: UserProfileIdProvider,
    private val baseMapper: ModelMapper<UserRole, BaseUserRoleModel>
): ActionUserRolesService {

    override fun changeUserRole(nickname: String, newRole: Role): BaseUserRoleModel {
        val role = transaction {
            val targetRole = roleRepository.getUserRoleByAlias(newRole.name)
                ?: throw RoleNotFoundException("Role with alias: ${newRole.name} not found")
            val currentUserRole = userToRoleRepository.getUserRole(nickname)

            if (currentUserRole == null) {
                createNewUserToRole(targetRole, nickname)
            } else {
                changeUserRole(targetRole, currentUserRole)
            }
            targetRole
        }
        return baseMapper.map(role)
    }

    private fun createNewUserToRole(role: UserRole, nickname: String) {
        val userId = userIdProvider.getUser(nickname)
        val roleId = role.id
        userToRoleRepository.createUserRole(userId, roleId)
    }

    private fun changeUserRole(newRole: UserRole, userToRole: UserToRole) {
        userToRole.role = newRole.id
        userToRoleRepository.save(userToRole)
    }
}