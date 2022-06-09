package com.between_freedom_and_space.mono_backend.common.exposed.callbacks.module

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.handler.EntityActionCallbackHandler
import org.koin.dsl.module

val exposedEntityCallbacksModule = module {
    single { EntityActionCallbackHandler() }
}