package com.between_freedom_and_space.mono_backend.posts.internal.comments.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.mappers.BaseCommentModelToCommentModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.mappers.CreateCommentRequestToCreateModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.mappers.UpdateCommentRequestToUpdateModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.CommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.CreateCommentRequest
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.UpdateCommentRequest
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InformationCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InteractionCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.impl.InformationCommentsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.impl.InteractionCommentsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.mappers.CommentEntityToBaseModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.CreateCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.UpdateCommentModel
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.math.sin

private val mappersModule = module {
    single<ModelMapper<PostComment, BaseCommentModel>> { CommentEntityToBaseModelMapper() }
    single<ModelMapper<BaseCommentModel, CommentModel>> { BaseCommentModelToCommentModelMapper() }
    single<ModelMapper<CreateCommentRequest, CreateCommentModel>> { CreateCommentRequestToCreateModelMapper() }
    single<ModelMapper<UpdateCommentRequest, UpdateCommentModel>> { UpdateCommentRequestToUpdateModelMapper() }
}

val commentsModule = module {
    includes(mappersModule)

    single { InformationCommentsServiceImpl() } bind InformationCommentsService::class
    single { InteractionCommentsServiceImpl() } bind InteractionCommentsService::class
}