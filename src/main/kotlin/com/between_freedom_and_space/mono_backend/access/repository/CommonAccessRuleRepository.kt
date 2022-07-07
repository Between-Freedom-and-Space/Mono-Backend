package com.between_freedom_and_space.mono_backend.access.repository

import com.between_freedom_and_space.mono_backend.access.entities.rules.models.AccessRule

interface CommonAccessRuleRepository {

    fun getAllAccessRules(pageNumber: Int, pageSize: Int): List<AccessRule>

    fun getAccessRuleById(id: Long): AccessRule?
}