package com.between_freedom_and_space.mono_backend.posts.internal.reactions.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.mappers.CreateCommentRequestToCreateModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.mappers.CreatePostRequestToCreateModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.mappers.UpdateCommentRequestToUpdateModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.mappers.UpdatePostRequestToUpdateModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.CreateCommentReactionRequest
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.CreatePostReactionRequest
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.UpdateCommentReactionRequest
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.api.models.UpdatePostReactionRequest
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment.CommentReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post.PostReaction
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
import org.h2.engine.Mode
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<CommentReaction, BaseCommentReactionModel>> { CommentReactionEntityToBaseModelMapper() }
    single<ModelMapper<PostReaction, BasePostReactionModel>> { PostReactionEntityToBaseModelMapper() }
    single<ModelMapper<CreateCommentReactionRequest, CreateCommentReactionModel>> { CreateCommentRequestToCreateModelMapper() }
    single<ModelMapper<CreatePostReactionRequest, CreatePostReactionModel>> { CreatePostRequestToCreateModelMapper() }
    single<ModelMapper<UpdateCommentReactionRequest, UpdateCommentReactionModel>> { UpdateCommentRequestToUpdateModelMapper() }
    single<ModelMapper<UpdatePostReactionRequest, UpdatePostReactionModel>> { UpdatePostRequestToUpdateModelMapper() }
}

val reactionsModule = module {
    includes(mappersModule)

    single { InformationCommentReactionsServiceImpl() } bind InformationCommentReactionsService::class
    single { InformationPostReactionsServiceImpl() } bind InformationPostReactionsService::class
    single { InteractionCommentReactionsServiceImpl() } bind InteractionCommentReactionsService::class
    single { InteractionPostReactionsServiceImpl() } bind InteractionPostReactionsService::class
}