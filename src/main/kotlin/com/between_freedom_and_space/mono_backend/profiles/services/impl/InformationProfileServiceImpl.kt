package com.between_freedom_and_space.mono_backend.profiles.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.services.InformationProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.CreationProfileException
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.ProfileNotFoundException
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import com.between_freedom_and_space.mono_backend.profiles.services.models.CreateProfileModel
import org.jetbrains.exposed.sql.transactions.transaction

class InformationProfileServiceImpl(
    private val repository: CommonProfilesRepository,
): InformationProfilesService {

    override fun getAllProfiles(pageNumber: Int, pageSize: Int): List<BaseProfileModel> {
        TODO("Not yet implemented")
    }

    override fun getProfileByNickName(nickName: String): BaseProfileModel {
        TODO("Not yet implemented")
    }

    override fun getProfileById(userId: Long): BaseProfileModel {
        TODO("Not yet implemented")
    }

    override fun getProfileSubscriptions(nickName: String, pageNumber: Int, pageSize: Int): List<BaseProfileModel> {
        TODO("Not yet implemented")
    }

    override fun getProfileSubscriptions(userId: Long, pageNumber: Int, pageSize: Int): List<BaseProfileModel> {
        TODO("Not yet implemented")
    }

    override fun getProfileSubscribers(nickName: String, pageNumber: Int, pageSize: Int): List<BaseProfileModel> {
        TODO("Not yet implemented")
    }

    override fun getProfileSubscribers(userId: Long, pageNumber: Int, pageSize: Int): List<BaseProfileModel> {
        TODO("Not yet implemented")
    }

    override fun getProfileSubscriptionsCount(nickName: String): Long {
        TODO("Not yet implemented")
    }

    override fun getProfileSubscriptionsCount(userId: Long): Long {
        TODO("Not yet implemented")
    }

    override fun getProfileSubscribersCount(nickName: String): Long {
        TODO("Not yet implemented")
    }

    override fun getProfileSubscribersCount(userId: Long): Long {
        TODO("Not yet implemented")
    }

    override fun getProfilePosts(nickName: String, pageNumber: Int, pageSize: Int): List<BasePostModel> {
        TODO("Not yet implemented")
    }

    override fun getProfilePosts(userId: Long, pageNumber: Int, pageSize: Int): List<BasePostModel> {
        TODO("Not yet implemented")
    }

    override fun getProfileComments(nickName: String, pageNumber: Int, pageSize: Int): List<BaseCommentModel> {
        TODO("Not yet implemented")
    }

    override fun getProfileComments(userId: Long, pageNumber: Int, pageSize: Int): List<BaseCommentModel> {
        TODO("Not yet implemented")
    }

    override fun getProfileTags(nickName: String, pageNumber: Int, pageSize: Int): List<BaseTagModel> {
        TODO("Not yet implemented")
    }

    override fun getProfileTags(userId: Long, pageNumber: Int, pageSize: Int): List<BaseTagModel> {
        TODO("Not yet implemented")
    }

    override fun getProfilePostReactions(
        nickName: String,
        pageNumber: Int,
        pageSize: Int
    ): List<BasePostReactionModel> {
        TODO("Not yet implemented")
    }

    override fun getProfilePostReactions(userId: Long, pageNumber: Int, pageSize: Int): List<BasePostReactionModel> {
        TODO("Not yet implemented")
    }

    override fun getProfileCommentReactions(
        nickName: String,
        pageNumber: Int,
        pageSize: Int
    ): List<BaseCommentReactionModel> {
        TODO("Not yet implemented")
    }

    override fun getProfileCommentReactions(
        userId: Long,
        pageNumber: Int,
        pageSize: Int
    ): List<BaseCommentReactionModel> {
        TODO("Not yet implemented")
    }
}