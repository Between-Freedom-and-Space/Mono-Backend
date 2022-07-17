package com.between_freedom_and_space.mono_backend.profiles.components

import org.jetbrains.exposed.dao.id.EntityID

interface UserProfileIdProvider {

    fun checkUserExists(nickname: String): Boolean

    fun checkUserExists(userId: Long): Boolean

    fun getUser(nickname: String): EntityID<Long>

    fun getUser(userId: Long): EntityID<Long>
}