package com.between_freedom_and_space.mono_backend.profiles.services.impl

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InformationCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationCommentReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InformationTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.services.InformationPostsService
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.profiles.entities.models.UserProfile
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.InformationProfileIconService
import com.between_freedom_and_space.mono_backend.profiles.internal.icon.services.models.BaseProfileIconModel
import com.between_freedom_and_space.mono_backend.profiles.repository.CommonProfilesRepository
import com.between_freedom_and_space.mono_backend.profiles.services.InformationProfilesService
import com.between_freedom_and_space.mono_backend.profiles.services.exceptions.ProfileNotFoundException
import com.between_freedom_and_space.mono_backend.profiles.services.models.BaseProfileModel
import org.jetbrains.exposed.sql.transactions.transaction

@Suppress("DuplicatedCode")
class InformationProfileServiceImpl(
    private val repository: CommonProfilesRepository,
    private val iconInformationService: InformationProfileIconService,
    private val postInformationService: InformationPostsService,
    private val commentsInformationService: InformationCommentsService,
    private val tagsInformationService: InformationTagsService,
    private val postReactionInformationService: InformationPostReactionsService,
    private val commentReactionInformationService: InformationCommentReactionsService,
    private val entityMapper: ModelMapper<UserProfile, BaseProfileModel>,
): InformationProfilesService {

    override fun getAllProfiles(pageNumber: Int, pageSize: Int): List<BaseProfileModel> {
        val profiles = transaction {
            repository.getAllProfiles(pageNumber, pageSize)
        }
        return profiles.map { entityMapper.map(it) }
    }

    override fun getProfileByNickName(nickName: String): BaseProfileModel {
        val profile = transaction { getProfileOrThrow(nickName) }
        return entityMapper.map(profile)
    }

    override fun getProfileById(userId: Long): BaseProfileModel {
        val profile = transaction { getProfileOrThrow(userId) }
        return entityMapper.map(profile)
    }

    override fun getProfileIconOrNull(nickname: String): BaseProfileIconModel? {
        return transaction {
            val profile = getProfileOrThrow(nickname)
            iconInformationService.getUserProfileIconOrNull(profile.id)
        }
    }

    override fun getProfileSubscriptions(nickName: String, pageNumber: Int, pageSize: Int): List<BaseProfileModel> {
        val subscriptions = transaction {
            val profile = getProfileOrThrow(nickName)
            val subscriptionEntities = profile.subscriptions
            val subscriptionIds = subscriptionEntities.map { it.subscribeOn }
            repository.getProfilesByIds(subscriptionIds)
        }

        return subscriptions.map { entityMapper.map(it) }
    }

    override fun getProfileSubscriptions(userId: Long, pageNumber: Int, pageSize: Int): List<BaseProfileModel> {
        val subscriptions = transaction {
            val profile = getProfileOrThrow(userId)
            val subscriptionEntities = profile.subscriptions
            val subscriptionIds = subscriptionEntities.map { it.subscribeOn }
            repository.getProfilesByIds(subscriptionIds)
        }

        return subscriptions.map { entityMapper.map(it) }
    }

    override fun getProfileSubscribers(nickName: String, pageNumber: Int, pageSize: Int): List<BaseProfileModel> {
        val subscribers = transaction {
            val profile = getProfileOrThrow(nickName)
            val subscribersEntity = profile.subscribers
            val subscribersIds = subscribersEntity.map { it.user }
            repository.getProfilesByIds(subscribersIds)
        }

        return subscribers.map { entityMapper.map(it) }
    }

    override fun getProfileSubscribers(userId: Long, pageNumber: Int, pageSize: Int): List<BaseProfileModel> {
        val subscribers = transaction {
            val profile = getProfileOrThrow(userId)
            val subscribersEntity = profile.subscribers
            val subscribersIds = subscribersEntity.map { it.user }
            repository.getProfilesByIds(subscribersIds)
        }

        return subscribers.map { entityMapper.map(it) }
    }

    override fun getProfileSubscriptionsCount(nickName: String): Long {
        return transaction {
            val profile = getProfileOrThrow(nickName)
            profile.subscriptions.count()
        }
    }

    override fun getProfileSubscriptionsCount(userId: Long): Long {
        return transaction {
            val profile = getProfileOrThrow(userId)
            profile.subscriptions.count()
        }
    }

    override fun getProfileSubscribersCount(nickName: String): Long {
        return transaction {
            val profile = getProfileOrThrow(nickName)
            profile.subscribers.count()
        }
    }

    override fun getProfileSubscribersCount(userId: Long): Long {
        return transaction {
            val profile = getProfileOrThrow(userId)
            profile.subscribers.count()
        }
    }

    override fun getProfilePosts(nickName: String, pageNumber: Int, pageSize: Int): List<BasePostModel> {
        return transaction {
            val profile = getProfileOrThrow(nickName)
            val profileId = profile.id.value
            postInformationService.getPostsWithAuthorId(profileId, pageNumber, pageSize)
        }
    }

    override fun getProfilePosts(userId: Long, pageNumber: Int, pageSize: Int): List<BasePostModel> {
        return transaction {
            getProfileOrThrow(userId)
            postInformationService.getPostsWithAuthorId(userId, pageNumber, pageSize)
        }
    }

    override fun getProfileComments(nickName: String, pageNumber: Int, pageSize: Int): List<BaseCommentModel> {
        return transaction {
            val profile = getProfileOrThrow(nickName)
            val profileId = profile.id.value
            commentsInformationService.getCommentsWithAuthorId(profileId, pageNumber, pageSize)
        }
    }

    override fun getProfileComments(userId: Long, pageNumber: Int, pageSize: Int): List<BaseCommentModel> {
        return transaction {
            getProfileOrThrow(userId)
            commentsInformationService.getCommentsWithAuthorId(userId, pageNumber, pageSize)
        }
    }

    override fun getProfileTags(nickName: String, pageNumber: Int, pageSize: Int): List<BaseTagModel> {
        return transaction {
            val profile = getProfileOrThrow(nickName)
            val profileId = profile.id.value
            tagsInformationService.getTagsWithAuthorId(profileId, pageNumber, pageSize)
        }
    }

    override fun getProfileTags(userId: Long, pageNumber: Int, pageSize: Int): List<BaseTagModel> {
        return transaction {
            getProfileOrThrow(userId)
            tagsInformationService.getTagsWithAuthorId(userId, pageNumber, pageSize)
        }
    }

    override fun getProfilePostReactions(
        nickName: String, pageNumber: Int, pageSize: Int
    ): List<BasePostReactionModel> {
        return transaction {
            val profile = getProfileOrThrow(nickName)
            val profileId = profile.id.value
            postReactionInformationService.getReactionsWithAuthorId(profileId, pageNumber, pageSize)
        }
    }

    override fun getProfilePostReactions(
        userId: Long, pageNumber: Int, pageSize: Int
    ): List<BasePostReactionModel> {
        return transaction {
            getProfileOrThrow(userId)
            postReactionInformationService.getReactionsWithAuthorId(userId, pageNumber, pageSize)
        }
    }

    override fun getProfileCommentReactions(
        nickName: String, pageNumber: Int, pageSize: Int
    ): List<BaseCommentReactionModel> {
        return transaction {
            val profile = getProfileOrThrow(nickName)
            val profileId = profile.id.value
            commentReactionInformationService.getReactionsWithAuthorId(profileId, pageNumber, pageSize)
        }
    }

    override fun getProfileCommentReactions(
        userId: Long, pageNumber: Int, pageSize: Int
    ): List<BaseCommentReactionModel> {
        return transaction {
            getProfileOrThrow(userId)
            commentReactionInformationService.getReactionsWithAuthorId(userId, pageNumber, pageSize)
        }
    }

    override fun getProfileSubscriptionsPosts(nickName: String, pageNumber: Int, pageSize: Int): List<BasePostModel> {
        return transaction {
            val profile = repository.getProfileByNickname(nickName)
                ?: throw ProfileNotFoundException("Profile with nickname: $nickName not found")
            val subscriptionIds = profile.subscriptions.map { it.subscribeOn }

            postInformationService.getPostsWithAuthorsIds(subscriptionIds, pageNumber, pageSize)
        }
    }

    private fun getProfileOrThrow(userId: Long): UserProfile {
        return repository.getProfileById(userId)
            ?: throw ProfileNotFoundException("Profile with id: $userId not found")
    }

    private fun getProfileOrThrow(nickName: String): UserProfile {
        return repository.getProfileByNickname(nickName)
            ?: throw ProfileNotFoundException("Profile with nickname: $nickName not found")
    }
}