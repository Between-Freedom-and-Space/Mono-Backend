package com.between_freedom_and_space.mono_backend.app

import com.between_freedom_and_space.mono_backend.auth.modules.authModule
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.module.exposedEntityCallbacksModule
import com.between_freedom_and_space.mono_backend.common.plugins.exceptionHandlerPluginModule
import com.between_freedom_and_space.mono_backend.posts.modules.postsModule
import com.between_freedom_and_space.mono_backend.profiles.modules.profilesModule
import com.between_freedom_and_space.mono_backend.recommendations.modules.recommendationModule
import com.between_freedom_and_space.mono_backend.util.module.utilModule
import org.koin.dsl.module

val applicationModule = module {
    includes(exceptionHandlerPluginModule)
//    includes(accessModule)
    includes(authModule)
    includes(postsModule)
    includes(profilesModule)
    includes(recommendationModule)
    includes(utilModule)
    includes(exposedEntityCallbacksModule)
}