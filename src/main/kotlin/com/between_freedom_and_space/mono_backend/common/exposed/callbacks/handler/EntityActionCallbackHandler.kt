package com.between_freedom_and_space.mono_backend.common.exposed.callbacks.handler

import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostCreated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostDeleted
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.PostUpdated
import com.between_freedom_and_space.mono_backend.common.exposed.callbacks.exceptions.InvalidEntityCallbackException
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.functions
import kotlin.reflect.jvm.jvmErasure

class EntityActionCallbackHandler {

    fun <T: Any> handleUpdate(entity: T, entityClass: KClass<*>) {
        val functions = entityClass.functions
        val functionWithAnnotations = functions.filter { function ->
            val annotations = function.annotations
            annotations.any { it is PostUpdated }
        }

        filterInvalidFunctions(functionWithAnnotations)
        functionWithAnnotations.forEach { function ->
            function.call(entity)
        }
    }

    fun <T: Any> handleCreate(entity: T, entityClass: KClass<*>) {
        val functions = entityClass.functions
        val functionWithAnnotations = functions.filter { function ->
            val annotations = function.annotations
            annotations.any { it is PostCreated }
        }

        filterInvalidFunctions(functionWithAnnotations)
        functionWithAnnotations.forEach { function ->
            function.call(entity)
        }
    }

    fun <T: Any> handleDelete(entity: T, entityClass: KClass<*>) {
        val functions = entityClass.functions
        val functionWithAnnotations = functions.filter { function ->
            val annotations = function.annotations
            annotations.any { it is PostDeleted }
        }

        filterInvalidFunctions(functionWithAnnotations)
        functionWithAnnotations.forEach { function ->
            function.call(entity)
        }
    }

    private fun filterInvalidFunctions(functions: List<KFunction<*>>) = functions.forEach { function ->
        if (function.parameters.size != 1) {
            throw InvalidEntityCallbackException("Entity callback must not have arguments")
        }

        if (function.returnType.jvmErasure != Unit::class) {
            throw InvalidEntityCallbackException("Entity callback return type must by Unit")
        }
    }
}