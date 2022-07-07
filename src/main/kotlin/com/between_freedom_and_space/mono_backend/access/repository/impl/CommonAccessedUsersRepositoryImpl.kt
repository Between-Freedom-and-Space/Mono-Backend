package com.between_freedom_and_space.mono_backend.access.repository.impl

import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule
import com.between_freedom_and_space.mono_backend.access.repository.CommonAccessedUsersRepository

class CommonAccessedUsersRepositoryImpl: CommonAccessedUsersRepository {

    override fun getAllUserRules(nickname: String): List<AccessRule> {
        TODO("Not yet implemented")
    }

    override fun getAllUserRules(userId: Long): List<AccessRule> {
        TODO("Not yet implemented")
    }
}