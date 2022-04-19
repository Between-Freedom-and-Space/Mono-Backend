package com.between_freedom_and_space.mono_backend.posts.internal.reactions.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.comment.CommentReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.post.PostReaction
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationCommentReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.InformationPostReactionsService
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl.InformationCommentReactionsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.impl.InformationPostReactionsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.mappers.CommentReactionEntityToBaseModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.mappers.PostReactionEntityToBaseModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BaseCommentReactionModel
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.service.model.BasePostReactionModel
import org.h2.engine.Mode
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<CommentReaction, BaseCommentReactionModel>> { CommentReactionEntityToBaseModelMapper() }
    single<ModelMapper<PostReaction, BasePostReactionModel>> { PostReactionEntityToBaseModelMapper() }
}

val reactionsModule = module {

    single { InformationCommentReactionsServiceImpl() } bind InformationCommentReactionsService::class
    single { InformationPostReactionsServiceImpl() } bind InformationPostReactionsService::class
}