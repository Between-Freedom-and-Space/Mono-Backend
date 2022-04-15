package com.between_freedom_and_space.mono_backend.access.service

import com.between_freedom_and_space.mono_backend.access.components.models.AccessVerifyResult

interface AccessService {

    fun havePostsAccess(): AccessVerifyResult

    fun haveProfileAccess(): AccessVerifyResult
}