package com.between_freedom_and_space.mono_backend.popular.modules

import com.between_freedom_and_space.mono_backend.popular.repository.LastCreatedRepository
import com.between_freedom_and_space.mono_backend.popular.repository.PopularRepository
import com.between_freedom_and_space.mono_backend.popular.repository.impl.LastCreatedRepositoryImpl
import com.between_freedom_and_space.mono_backend.popular.repository.impl.StubPopularRepository
import com.between_freedom_and_space.mono_backend.popular.service.LastCreatedService
import com.between_freedom_and_space.mono_backend.popular.service.PopularService
import com.between_freedom_and_space.mono_backend.popular.service.impl.LastCreatedServiceImpl
import com.between_freedom_and_space.mono_backend.popular.service.impl.StubPopularService
import com.between_freedom_and_space.mono_backend.posts.internal.comments.modules.qualifiers.CommentsMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.internal.tags.modules.qualifiers.TagsMappersQualifiers
import com.between_freedom_and_space.mono_backend.posts.modules.qualifiers.PostMappersQualifiers
import com.between_freedom_and_space.mono_backend.profiles.modules.qualifiers.ProfilesMappersQualifiers
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private val repositoryModule = module {
    single { LastCreatedRepositoryImpl() } bind LastCreatedRepository::class
    single { StubPopularRepository() } bind PopularRepository::class
}

private val serviceModule = module {
    single { LastCreatedServiceImpl(
        get(),
        get(named(PostMappersQualifiers.POST_TO_BASE_POST_MODEL)),
        get(named(ProfilesMappersQualifiers.USER_PROFILE_TO_BASE_PROFILE)),
        get(named(TagsMappersQualifiers.POST_TAG_TO_BASE_MODEL)),
        get(named(CommentsMappersQualifiers.POST_COMMENT_TO_BASE_MODEL))
    ) } bind LastCreatedService::class
    single { StubPopularService(
        get(),
        get(named(PostMappersQualifiers.POST_TO_BASE_POST_MODEL)),
        get(named(ProfilesMappersQualifiers.USER_PROFILE_TO_BASE_PROFILE)),
        get(named(TagsMappersQualifiers.POST_TAG_TO_BASE_MODEL)),
        get(named(CommentsMappersQualifiers.POST_COMMENT_TO_BASE_MODEL))
    ) } bind PopularService::class
}

val popularModule = module {
    includes(repositoryModule)
    includes(serviceModule)
}