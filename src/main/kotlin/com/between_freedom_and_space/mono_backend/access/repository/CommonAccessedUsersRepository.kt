package com.between_freedom_and_space.mono_backend.access.repository

import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessedUser
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateUserRuleEntityModel
import org.jetbrains.exposed.dao.id.EntityID

interface CommonAccessedUsersRepository {

    fun getAllUserRules(userId: EntityID<Long>, includeNotActive: Boolean = false): List<AccessRule>

    fun getUserRuleById(userRuleId: Long, includeNotActive: Boolean = false): AccessedUser?

    fun createUserRule(authorId: EntityID<Long>, userId: EntityID<Long>, model: CreateUserRuleEntityModel): AccessedUser

    fun deleteUserRule(authorId: EntityID<Long>, userRuleId: Long): AccessedUser?

    fun saveUserRule(userRule: AccessedUser): AccessedUser
}