package com.between_freedom_and_space.mono_backend.posts.internal.tags.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.mappers.BaseTagModelToTagModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.mappers.CreateTagRequestToCreateModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.mappers.UpdateTagRequestToUpdateModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.CreateTagRequest
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.TagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.UpdateTagRequest
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.modules.qualifiers.TagsMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.CommonPostToTagRepository
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.CommonTagsRepository
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.impl.CommonPostToTagRepositoryImpl
import com.between_freedom_and_space.mono_backend.posts.internal.tags.repository.impl.CommonTagsRepositoryImpl
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.ActionTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InformationTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InteractionPostToTagService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InteractionTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.impl.ActionTagsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.impl.InformationTagsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.impl.InteractionPostToTagServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.impl.InteractionTagsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.mappers.TagEntityToBaseModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.CreateTagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.UpdateTagModel
import com.between_freedom_and_space.mono_backend.posts.repository.CommonPostRepository
import com.between_freedom_and_space.mono_backend.posts.repository.impl.CommonPostRepositoryImpl
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<PostTag, BaseTagModel>>(
        named(TagsMappersQualifiers.POST_TAG_TO_BASE_MODEL)
    ) { TagEntityToBaseModelMapper() }
    single<ModelMapper<BaseTagModel, TagModel>>(
        named(TagsMappersQualifiers.BASE_TAG_MODEL_TO_MODEL)
    ) { BaseTagModelToTagModelMapper() }
    single<ModelMapper<CreateTagRequest, CreateTagModel>>(
        named(TagsMappersQualifiers.CREATE_TAG_REQUEST_TO_MODEL)
    ) { CreateTagRequestToCreateModelMapper() }
    single<ModelMapper<UpdateTagRequest, UpdateTagModel>>(
        named(TagsMappersQualifiers.UPDATE_TAG_REQUEST_TO_MODEL)
    ) { UpdateTagRequestToUpdateModelMapper() }
}

private val serviceModule = module {
    single { InformationTagsServiceImpl(
        get(),
        get(named(TagsMappersQualifiers.POST_TAG_TO_BASE_MODEL))
    ) } bind InformationTagsService::class
    single { InteractionTagsServiceImpl(
        get(), get(),
        get(named(TagsMappersQualifiers.POST_TAG_TO_BASE_MODEL))
    ) } bind InteractionTagsService::class
    single { ActionTagsServiceImpl(
        get(), get(named(TagsMappersQualifiers.POST_TAG_TO_BASE_MODEL))
    ) } bind ActionTagsService::class
    single { InteractionPostToTagServiceImpl(
        get(), get(), get(),
        get(named(TagsMappersQualifiers.POST_TAG_TO_BASE_MODEL))
    ) } bind InteractionPostToTagService::class
}

private val repositoryModule = module {
    single { CommonTagsRepositoryImpl() } bind CommonTagsRepository::class
    single { CommonPostToTagRepositoryImpl() } bind CommonPostToTagRepository::class
}

val tagsModule = module {
    includes(mappersModule)
    includes(serviceModule)
    includes(repositoryModule)
}