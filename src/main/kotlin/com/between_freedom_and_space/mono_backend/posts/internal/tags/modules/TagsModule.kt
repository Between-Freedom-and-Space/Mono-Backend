package com.between_freedom_and_space.mono_backend.posts.internal.tags.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.entities.models.PostTag
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.InformationTagsService
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.impl.InformationTagsServiceImpl
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.mappers.TagEntityToBaseModelMapper
import com.between_freedom_and_space.mono_backend.posts.internal.tags.services.model.BaseTagModel
import org.koin.dsl.bind
import org.koin.dsl.module

val tagsModule = module {
    single<ModelMapper<PostTag, BaseTagModel>> { TagEntityToBaseModelMapper() }

    single { InformationTagsServiceImpl() } bind InformationTagsService::class
}