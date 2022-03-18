package com.between_freedom_and_space.mono_backend.access.service

import com.between_freedom_and_space.mono_backend.access.models.AccessResult

interface AccessService {

    fun havePostsAccess(): AccessResult

    fun haveProfileAccess(): AccessResult
}