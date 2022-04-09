package com.between_freedom_and_space.mono_backend.profiles.repository

import com.between_freedom_and_space.mono_backend.profiles.entities.UserProfile

interface CommonProfilesRepository {

    fun getProfileByNickname(nickName: String): UserProfile?

    fun getProfileById(id: Long): UserProfile?
}