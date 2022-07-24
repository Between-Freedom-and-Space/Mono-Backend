package com.between_freedom_and_space.mono_backend.posts.internal.reactions.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.mappers.*
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.*
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment.CommentReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post.PostReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.modules.qualifiers.ReactionsMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.CommonCommentReactionsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.CommonPostReactionsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.impl.CommonCommentReactionsRepositoryImpl
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.repository.impl.CommonPostReactionsRepositoryImpl
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationCommentReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InteractionCommentReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InteractionPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl.InformationCommentReactionsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl.InformationPostReactionsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl.InteractionCommentReactionsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl.InteractionPostReactionsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.mappers.CommentReactionEntityToBaseModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.mappers.PostReactionEntityToBaseModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.*
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<CommentReaction, BaseCommentReactionModel>>(
        named(ReactionsMappersQualifiers.COMMENT_REACTION_TO_BASE_MODEL)
    ) { CommentReactionEntityToBaseModelMapper() }
    single<ModelMapper<PostReaction, BasePostReactionModel>>(
        named(ReactionsMappersQualifiers.POST_REACTION_TO_BASE_MODEL)
    ) { PostReactionEntityToBaseModelMapper() }
    single<ModelMapper<CreateCommentReactionRequest, CreateCommentReactionModel>>(
        named(ReactionsMappersQualifiers.CREATE_COMMENT_REACTION_REQUEST_TO_MODEL)
    ) { CreateCommentRequestToCreateModelMapper() }
    single<ModelMapper<CreatePostReactionRequest, CreatePostReactionModel>>(
        named(ReactionsMappersQualifiers.CREATE_POST_REACTION_REQUEST_TO_MODEL)
    ) { CreatePostRequestToCreateModelMapper() }
    single<ModelMapper<UpdateCommentReactionRequest, UpdateCommentReactionModel>>(
        named(ReactionsMappersQualifiers.UPDATE_COMMENT_REACTION_REQUEST_TO_MODEL)
    ) { UpdateCommentRequestToUpdateModelMapper() }
    single<ModelMapper<UpdatePostReactionRequest, UpdatePostReactionModel>>(
        named(ReactionsMappersQualifiers.UPDATE_POST_REACTION_REQUEST_TO_MODEL)
    ) { UpdatePostRequestToUpdateModelMapper() }
    single<ModelMapper<BaseCommentReactionModel, ReactionModel>>(
        named(ReactionsMappersQualifiers.BASE_COMMENT_REACTION_TO_MODEL)
    ) { BaseCommentReactionModelToReactionModelMapper() }
    single<ModelMapper<BasePostReactionModel, ReactionModel>>(
        named(ReactionsMappersQualifiers.BASE_POST_REACTION_TO_MODEL)
    ) { BasePostReactionModelToReactionModelMapper() }
}

private val serviceModule = module {
    single { InformationCommentReactionsServiceImpl(
        get(),
        get(named(ReactionsMappersQualifiers.COMMENT_REACTION_TO_BASE_MODEL))
    )
    } bind InformationCommentReactionsService::class
    single { InformationPostReactionsServiceImpl(
        get(),
        get(named(ReactionsMappersQualifiers.POST_REACTION_TO_BASE_MODEL))
    )
    } bind InformationPostReactionsService::class
    single { InteractionCommentReactionsServiceImpl(
        get(), get(), get(),
        get(named(ReactionsMappersQualifiers.COMMENT_REACTION_TO_BASE_MODEL))
    ) } bind InteractionCommentReactionsService::class
    single { InteractionPostReactionsServiceImpl(
        get(), get(), get(),
        get(named(ReactionsMappersQualifiers.POST_REACTION_TO_BASE_MODEL))
    ) } bind InteractionPostReactionsService::class
}

private val repositoryModule = module {
    single { CommonCommentReactionsRepositoryImpl() } bind CommonCommentReactionsRepository::class
    single { CommonPostReactionsRepositoryImpl() } bind CommonPostReactionsRepository::class
}

val reactionsModule = module {
    includes(mappersModule)
    includes(serviceModule)
    includes(repositoryModule)
}