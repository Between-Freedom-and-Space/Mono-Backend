package com.between_freedom_and_space.mono_backend.posts.internal.comments.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InformationCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.impl.InformationCommentsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.mappers.CommentEntityToBaseModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import org.koin.dsl.bind
import org.koin.dsl.module

val commentsModule = module {

    single<ModelMapper<PostComment, BaseCommentModel>> { CommentEntityToBaseModelMapper() }

    single { InformationCommentsServiceImpl() } bind InformationCommentsService::class
}