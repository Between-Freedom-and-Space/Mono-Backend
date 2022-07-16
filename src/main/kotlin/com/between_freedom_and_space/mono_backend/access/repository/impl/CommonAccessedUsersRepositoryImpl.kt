package com.between_freedom_and_space.mono_backend.access.repository.impl

import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessedUser
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessedUsersRepository
import com.between_freedom_and_space.mono_backend.access.repository.models.CreateUserRuleEntityModel
import org.jetbrains.exposed.dao.id.EntityID

class CommonAccessedUsersRepositoryImpl: CommonAccessedUsersRepository {

    override fun getAllUserRules(nickname: String): List<AccessRule> {
        TODO("Not yet implemented")
    }

    override fun getAllUserRules(userId: Long): List<AccessRule> {
        TODO("Not yet implemented")
    }

    override fun createUserRule(
        authorId: EntityID<Long>,
        userId: EntityID<Long>,
        model: CreateUserRuleEntityModel
    ): AccessedUser {
        TODO("Not yet implemented")
    }

    override fun deleteUserRule(userRule: Long): AccessedUser? {
        TODO("Not yet implemented")
    }
}