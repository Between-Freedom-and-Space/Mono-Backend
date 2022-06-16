package com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.handler.EntityActionCallbackHandler
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.EntityChangeType.*
import org.jetbrains.exposed.dao.id.LongIdTable

abstract class CallbackLongEntityClass<E: LongEntity>(table: LongIdTable): LongEntityClass<E>(table) {

    private val handler by inject<EntityActionCallbackHandler>()

    init {
        EntityHook.subscribe { action ->
            val entity = action.toEntity(this)
                ?: return@subscribe
            val entityClass = entity::class

            when (action.changeType) {
                Updated -> handler.handleUpdate(entity, entityClass)
                Created -> handler.handleCreate(entity, entityClass)
                Removed -> handler.handleDelete(entity, entityClass)
            }
        }
    }
}