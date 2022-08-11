package com.between_freedom_and_space.mono_backend.profiles.services

import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel

interface InformationProfilesService {

    fun getAllProfiles(pageNumber: Int, pageSize: Int): List<BaseProfileModel>

    fun getProfileByNickName(nickName: String): BaseProfileModel

    fun getProfileById(userId: Long): BaseProfileModel

    fun getProfileIconOrNull(nickname: String): BaseProfileIconModel?

    fun getProfileSubscriptions(nickName: String, pageNumber: Int, pageSize: Int): List<BaseProfileModel>

    fun getProfileSubscriptions(userId: Long, pageNumber: Int, pageSize: Int): List<BaseProfileModel>

    fun getProfileSubscribers(nickName: String, pageNumber: Int, pageSize: Int): List<BaseProfileModel>

    fun getProfileSubscribers(userId: Long, pageNumber: Int, pageSize: Int): List<BaseProfileModel>

    fun getProfileSubscriptionsCount(nickName: String): Long

    fun getProfileSubscriptionsCount(userId: Long): Long

    fun getProfileSubscribersCount(nickName: String): Long

    fun getProfileSubscribersCount(userId: Long): Long

    fun getProfilePosts(nickName: String, pageNumber: Int, pageSize: Int): List<BasePostModel>

    fun getProfilePosts(userId: Long, pageNumber: Int, pageSize: Int): List<BasePostModel>

    fun getProfileComments(nickName: String, pageNumber: Int, pageSize: Int): List<BaseCommentModel>

    fun getProfileComments(userId: Long, pageNumber: Int, pageSize: Int): List<BaseCommentModel>

    fun getProfileTags(nickName: String, pageNumber: Int, pageSize: Int): List<BaseTagModel>

    fun getProfileTags(userId: Long, pageNumber: Int, pageSize: Int): List<BaseTagModel>

    fun getProfilePostReactions(nickName: String, pageNumber: Int, pageSize: Int): List<BasePostReactionModel>

    fun getProfilePostReactions(userId: Long, pageNumber: Int, pageSize: Int): List<BasePostReactionModel>

    fun getProfileCommentReactions(nickName: String, pageNumber: Int, pageSize: Int): List<BaseCommentReactionModel>

    fun getProfileCommentReactions(userId: Long, pageNumber: Int, pageSize: Int): List<BaseCommentReactionModel>

    fun getProfileSubscriptionsPosts(nickName: String, pageNumber: Int, pageSize: Int): List<BasePostModel>
}