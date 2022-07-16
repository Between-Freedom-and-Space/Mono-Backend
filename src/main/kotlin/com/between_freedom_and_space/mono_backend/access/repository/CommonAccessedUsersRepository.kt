package com.between_freedom_and_space.mono_backend.access.repository

import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessedUser
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateUserRuleEntityModel
import org.jetbrains.exposed.dao.id.EntityID

interface CommonAccessedUsersRepository {

    fun getAllUserRules(nickname: String): List<AccessRule>

    fun getAllUserRules(userId: Long): List<AccessRule>

    fun createUserRule(authorId: EntityID<Long>, userId: EntityID<Long>, model: CreateUserRuleEntityModel): AccessedUser

    fun deleteUserRule(userRule: Long): AccessedUser?
}