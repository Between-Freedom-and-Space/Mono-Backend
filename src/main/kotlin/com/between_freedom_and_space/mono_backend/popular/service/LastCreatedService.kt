package com.between_freedom_and_space.mono_backend.popular.service

import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel

interface LastCreatedService {

    fun getLastCreatedPosts(pageNumber: Int, pageSize: Int): List<BasePostModel>

    fun getLastCreatedProfiles(pageNumber: Int, pageSize: Int): List<BaseProfileModel>

    fun getLastCreatedTags(pageNumber: Int, pageSize: Int): List<BaseTagModel>

    fun getLastCreatedComments(pageNumber: Int, pageSize: Int): List<BaseCommentModel>
}