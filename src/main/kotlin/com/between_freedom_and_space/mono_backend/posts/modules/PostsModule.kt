package com.between_freedom_and_space.mono_backend.posts.modules

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.mappers.CreatePostRequestToCreateModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.mappers.PostReactionsCountToReactionsCountResponseMapper
import com.between_freedom_and_space.mono_backend.posts.api.mappers.UpdatePostRequestToUpdateModelMapper
import com.between_freedom_and_space.mono_backend.posts.api.models.CreatePostRequest
import com.between_freedom_and_space.mono_backend.posts.api.models.PostReactionsCountResponse
import com.between_freedom_and_space.mono_backend.posts.api.models.UpdatePostRequest
import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.internal.comments.modules.commentsModule
import com.between_freedom_and_space.mono_backend.posts.internal.reactions.modules.reactionsModule
import com.between_freedom_and_space.mono_backend.posts.internal.tags.modules.tagsModule
import com.between_freedom_and_space.mono_backend.posts.services.mappers.PostEntityToBasePostModelMapper
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.CreatePostModel
import com.between_freedom_and_space.mono_backend.posts.services.models.PostReactionsCountModel
import com.between_freedom_and_space.mono_backend.posts.services.models.UpdatePostModel
import org.koin.dsl.module

private val mappersModule = module {
    single<ModelMapper<Post, BasePostModel>> { PostEntityToBasePostModelMapper() }
    single<ModelMapper<CreatePostRequest, CreatePostModel>> { CreatePostRequestToCreateModelMapper() }
    single<ModelMapper<PostReactionsCountModel, PostReactionsCountResponse>> { PostReactionsCountToReactionsCountResponseMapper() }
    single<ModelMapper<UpdatePostRequest, UpdatePostModel>> { UpdatePostRequestToUpdateModelMapper() }
}

val postsModule = module {
    includes(commentsModule)
    includes(tagsModule)
    includes(reactionsModule)

    includes(mappersModule)

}