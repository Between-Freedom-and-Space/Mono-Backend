package com.between_freedom_and_space.mono_backend.common.exposed.callbacks.handler

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostCreated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostDeleted
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.exceptions.InvalidEntityCallbackException
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.functions

class EntityActionCallbackHandler {

    fun handleUpdate(entityClass: KClass<*>) {
        val functions = entityClass.functions
        val functionWithAnnotations = functions.filter { function ->
            val annotations = function.annotations
            annotations.any { it is PostUpdated }
        }

        filterInvalidFunctions(functionWithAnnotations)
        functionWithAnnotations.forEach(KFunction<*>::call)
    }

    fun handleCreate(entityClass: KClass<*>) {
        val functions = entityClass.functions
        val functionWithAnnotations = functions.filter { function ->
            val annotations = function.annotations
            annotations.any { it is PostCreated }
        }

        filterInvalidFunctions(functionWithAnnotations)
        functionWithAnnotations.forEach(KFunction<*>::call)
    }

    fun handleDelete(entityClass: KClass<*>) {
        val functions = entityClass.functions
        val functionWithAnnotations = functions.filter { function ->
            val annotations = function.annotations
            annotations.any { it is PostDeleted }
        }

        filterInvalidFunctions(functionWithAnnotations)
        functionWithAnnotations.forEach(KFunction<*>::call)
    }

    private fun filterInvalidFunctions(functions: List<KFunction<*>>) = functions.forEach { function ->
        if (function.parameters.isNotEmpty()) {
            throw InvalidEntityCallbackException("Entity callback must not have arguments")
        }

        if (function.returnType != Unit::class) {
            throw InvalidEntityCallbackException("Entity callback return type must by Unit")
        }
    }
}