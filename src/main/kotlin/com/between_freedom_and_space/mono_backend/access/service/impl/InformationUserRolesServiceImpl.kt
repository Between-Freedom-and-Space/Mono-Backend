package com.between_freedom_and_space.mono_backend.access.service.impl

import com.between_freedom_and_space.mono_backend.access.entities.role.models.UserRole
import com.between_freedom_and_space.mono_backend.access.repository.CommonUserRoleRepository
import com.between_freedom_and_space.mono_backend.access.repository.CommonUserToRoleRepository
import com.between_freedom_and_space.mono_backend.access.service.InformationUserRolesService
import com.between_freedom_and_space.mono_backend.access.service.exception.RoleNotFoundException
import com.between_freedom_and_space.mono_backend.access.service.models.BaseUserRoleModel
import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import org.jetbrains.exposed.sql.transactions.transaction

class InformationUserRolesServiceImpl(
    private val userRoleRepository: CommonUserRoleRepository,
    private val userToRoleRepository: CommonUserToRoleRepository,
    private val baseMapper: ModelMapper<UserRole, BaseUserRoleModel>
): InformationUserRolesService {

    override fun getAllRoles(): Collection<BaseUserRoleModel> {
        val roles = transaction {
            userRoleRepository.getAllUserRoles()
        }
        return roles.map { baseMapper.map(it) }
    }

    override fun getRoleById(roleId: Long): BaseUserRoleModel {
        val role = transaction { getUserRoleOrThrow(roleId) }
        return baseMapper.map(role)
    }

    override fun getUserRole(userId: Long): BaseUserRoleModel {
        val role = transaction {
            val userToRole = userToRoleRepository.getUserRole(userId)
                ?: throw RoleNotFoundException("Role for user with id: $userId not found")
            val roleId = userToRole.role.value
            getUserRoleOrThrow(roleId)
        }
        return baseMapper.map(role)
    }

    override fun getUserRole(nickname: String): BaseUserRoleModel {
        val role = transaction {
            val userToRole = userToRoleRepository.getUserRole(nickname)
                ?: throw RoleNotFoundException("Role for user with nickname: $nickname not found")
            val roleId = userToRole.id.value
            getUserRoleOrThrow(roleId)
        }
        return baseMapper.map(role)
    }

    private fun getUserRoleOrThrow(roleId: Long): UserRole {
        return userRoleRepository.getUserRoleById(roleId)
            ?: throw RoleNotFoundException("Role with id: $roleId not found")
    }
}