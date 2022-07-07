package com.between_freedom_and_space.mono_backend.posts.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.mappers.*
import com.between_freedom_and_space.mono_backend.posts.api.models.*
import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.internal.comments.modules.commentsModule
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.modules.reactionsModule
import com.between_freedom_and_space.mono_backend.posts.internal.tags.modules.tagsModule
import com.between_freedom_and_space.mono_backend.posts.modules.qualifiers.PostMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.repository.CommonPostRepository
import com.between_freedom_and_space.mono_backend.posts.repository.impl.CommonPostRepositoryImpl
import com.between_freedom_and_space.mono_backend.posts.repository.models.CreatePostEntityModel
import com.between_freedom_and_space.mono_backend.posts.services.ActionPostsService
import com.between_freedom_and_space.mono_backend.posts.services.InformationPostsService
import com.between_freedom_and_space.mono_backend.posts.services.InteractionPostsService
import com.between_freedom_and_space.mono_backend.posts.services.impl.ActionPostsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.services.impl.InformationPostsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.services.impl.InteractionPostServiceImpl
import com.between_freedom_and_space.mono_backend.posts.services.mappers.CreatePostModelToCreateEntityMapper
import com.between_freedom_and_space.mono_backend.posts.services.mappers.PostEntityToBasePostModelMapper
import com.between_freedom_and_space.mono_backend.posts.services.models.*
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<Post, BasePostModel>>(
        named(PostMappersQualifiers.POST_TO_BASE_POST_MODEL)
    ) { PostEntityToBasePostModelMapper() }
    single<ModelMapper<CreatePostRequest, CreatePostModel>>(
        named(PostMappersQualifiers.CREATE_POST_REQUEST_TO_CREATE_MODEL)
    ) { CreatePostRequestToCreateModelMapper() }
    single<ModelMapper<PostReactionsCountModel, PostReactionsCountResponse>>(
        named(PostMappersQualifiers.POST_REACTIONS_COUNT_MODEL_TO_POST_REACTIONS_COUNT_RESPONSE)
    ) { PostReactionsCountToReactionsCountResponseMapper() }
    single<ModelMapper<UpdatePostRequest, UpdatePostModel>>(
        named(PostMappersQualifiers.UPDATE_POST_REQUEST_TO_UPDATE_MODEL)
    ) { UpdatePostRequestToUpdateModelMapper() }
    single<ModelMapper<BasePostModel, PostModel>>(
        named(PostMappersQualifiers.BASE_POST_MODEL_TO_POST_MODEL)
    ) { BasePostModelToPostModelMapper() }
    single<ModelMapper<CreatePostModel, CreatePostEntityModel>>(
        named(PostMappersQualifiers.CREATE_POST_MODEL_TO_CREATE_ENTITY)
    ) { CreatePostModelToCreateEntityMapper() }
    single<ModelMapper<PostCommentsCountModel, PostCommentsCountResponse>>(
        named(PostMappersQualifiers.POST_COMMENTS_COUNT_MODEL_TO_POST_COMMENTS_COUNT_RESPONSE)
    ) { PostCommentsCountToCommentsCountResponse() }
}

private val serviceModule = module {
    single { InformationPostsServiceImpl(
        get(), get(named(PostMappersQualifiers.POST_TO_BASE_POST_MODEL)),
        get(), get(), get()
    ) } bind InformationPostsService::class
    single { ActionPostsServiceImpl(
        get(), get(), get(),
        get(named(PostMappersQualifiers.POST_TO_BASE_POST_MODEL))
    ) } bind ActionPostsService::class
    single { InteractionPostServiceImpl(
        get(), get(), get(), get(), get(),
        get(named(PostMappersQualifiers.POST_TO_BASE_POST_MODEL)),
        get(named(PostMappersQualifiers.CREATE_POST_MODEL_TO_CREATE_ENTITY))
    ) } bind InteractionPostsService::class
}

private val repositoryModule = module {
    single { CommonPostRepositoryImpl() } bind CommonPostRepository::class
}

val postsModule = module {
    includes(commentsModule)
    includes(tagsModule)
    includes(reactionsModule)

    includes(mappersModule)
    includes(serviceModule)
    includes(repositoryModule)
}