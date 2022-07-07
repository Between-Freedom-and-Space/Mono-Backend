package com.between_freedom_and_space.mono_backend.posts.internal.comments.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.mappers.BaseCommentModelToCommentModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.mappers.CreateCommentRequestToCreateModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.mappers.UpdateCommentRequestToUpdateModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.CommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.CreateCommentRequest
import com.between_freedom_and_space.mono_backend.posts.internal.comments.api.models.UpdateCommentRequest
import com.between_freedom_and_space.mono_backend.posts.internal.comments.entities.PostComment
import com.between_freedom_and_space.mono_backend.posts.internal.comments.modules.qualifiers.CommentsMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.internal.comments.repository.CommonCommentsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.comments.repository.impl.CommonCommentsRepositoryImpl
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InformationCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.InteractionCommentsService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.impl.InformationCommentsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.impl.InteractionCommentsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.mappers.CommentEntityToBaseModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.BaseCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.CreateCommentModel
import com.between_freedom_and_space.mono_backend.posts.internal.comments.services.models.UpdateCommentModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<PostComment, BaseCommentModel>>(
        named(CommentsMappersQualifiers.POST_COMMENT_TO_BASE_MODEL)
    ) { CommentEntityToBaseModelMapper() }
    single<ModelMapper<BaseCommentModel, CommentModel>>(
        named(CommentsMappersQualifiers.BASE_COMMENT_TO_COMMENT_MODEL)
    ) { BaseCommentModelToCommentModelMapper() }
    single<ModelMapper<CreateCommentRequest, CreateCommentModel>>(
        named(CommentsMappersQualifiers.CREATE_COMMENT_REQUEST_TO_CREATE_MODEL)
    ) { CreateCommentRequestToCreateModelMapper() }
    single<ModelMapper<UpdateCommentRequest, UpdateCommentModel>>(
        named(CommentsMappersQualifiers.UPDATE_COMMENT_REQUEST_TO_UPDATE_MODEL)
    ) { UpdateCommentRequestToUpdateModelMapper() }
}

private val serviceModule = module {
    single { InformationCommentsServiceImpl(
        get(),
        get(named(CommentsMappersQualifiers.POST_COMMENT_TO_BASE_MODEL))
    )
    } bind InformationCommentsService::class
    single { InteractionCommentsServiceImpl(
        get(), get(), get(),
        get(named(CommentsMappersQualifiers.POST_COMMENT_TO_BASE_MODEL))
    ) } bind InteractionCommentsService::class
}

private val repositoryModule = module {
    single { CommonCommentsRepositoryImpl() } bind CommonCommentsRepository::class
}

val commentsModule = module {
    includes(mappersModule)
    includes(serviceModule)
    includes(repositoryModule)
}