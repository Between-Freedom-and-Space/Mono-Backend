package com.between_freedom_and_space.mono_backend.common.exposed.callbacks.base

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.handler.EntityActionCallbackHandler
import com.between_freedom_and_space.mono_backend.util.extensions.inject
import org.jetbrains.exposed.dao.EntityChangeType.*
import org.jetbrains.exposed.dao.EntityHook
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.toEntity

abstract class CallbackIntEntityClass<E: IntEntity>(table: IntIdTable): IntEntityClass<E>(table) {

    private val handler by inject<EntityActionCallbackHandler>()

    init {
        EntityHook.subscribe { action ->
            val entity = action.toEntity(this)
                ?: return@subscribe
            val entityClass = entity::class
            when(action.changeType) {
                Updated -> handler.handleUpdate(entity, entityClass)
                Created -> handler.handleCreate(entity, entityClass)
                Removed -> handler.handleDelete(entity, entityClass)
            }
        }
    }
}