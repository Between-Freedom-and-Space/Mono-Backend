package com.between_freedom_and_space.mono_backend.posts.internal.tags.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.mappers.BaseTagModelToTagModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.mappers.CreateTagRequestToCreateModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.mappers.UpdateTagRequestToUpdateModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.CreateTagRequest
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.TagModel
import com.between_freedom_and_space.mono_backend.posts.internal.tags.api.models.UpdateTagRequest
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
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
import org.koin.dsl.bind
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<PostTag, BaseTagModel>> { TagEntityToBaseModelMapper() }
    single<ModelMapper<BaseTagModel, TagModel>> { BaseTagModelToTagModelMapper() }
    single<ModelMapper<CreateTagRequest, CreateTagModel>> { CreateTagRequestToCreateModelMapper() }
    single<ModelMapper<UpdateTagRequest, UpdateTagModel>> { UpdateTagRequestToUpdateModelMapper() }
}

private val serviceModule = module {
    single { InformationTagsServiceImpl(get(), get()) } bind InformationTagsService::class
    single { InteractionTagsServiceImpl(get(), get()) } bind InteractionTagsService::class
    single { ActionTagsServiceImpl(get(), get()) } bind ActionTagsService::class
    single { InteractionPostToTagServiceImpl(get(), get(), get(), get()) } bind InteractionPostToTagService::class
}

val tagsModule = module {
    includes(mappersModule)
    includes(serviceModule)
}