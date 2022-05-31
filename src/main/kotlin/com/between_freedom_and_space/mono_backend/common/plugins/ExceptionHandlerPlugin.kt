package com.between_freedom_and_space.mono_backend.common.plugins

import com.between_freedom_and_space.mono_backend.util.extensions.inject
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.util.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import mu.KotlinLogging
import kotlin.reflect.KClass

typealias ExceptionHandler = suspend (ApplicationCall, Exception) -> Unit

class ExceptionHandlerPlugin(
    config: Configuration,
    private val exceptionInterceptor: ExceptionInterceptor,
) {
    companion object: Plugin<ApplicationCallPipeline, Configuration, ExceptionHandlerPlugin> {

        override val key = AttributeKey<ExceptionHandlerPlugin>("ApplicationExceptionHandlerPlugin")

        override fun install(
            pipeline: ApplicationCallPipeline, configure: Configuration.() -> Unit
        ): ExceptionHandlerPlugin {
            val handler by inject<ExceptionInterceptor>()
            val config = Configuration().apply(configure)
            val plugin = ExceptionHandlerPlugin(config, handler)

            pipeline.intercept(ApplicationCallPipeline.Plugins) {
                plugin.intercept(this)
            }

            return plugin
        }
    }

    data class Configuration(
        var enableLogging: Boolean = true,
        var handlers: Map<KClass<*>, ExceptionHandler> = emptyMap(),
        var defaultHandler: ExceptionHandler? = null,
    )

    private val handlers = config.handlers.toMutableMap()

    private val defaultHandler = config.defaultHandler

    private val enableLogging = config.enableLogging

    private val logger = KotlinLogging.logger {  }

    fun <T: Exception> addHandler(handler: ExceptionHandler, clazz: KClass<T>) {
        handlers[clazz] = handler
    }

    private suspend fun intercept(context: PipelineContext<Unit, ApplicationCall>) {
        try {
            context.proceed()
        } catch (ex: Exception) {
            val call = context.call
            exceptionInterceptor.handle(ex, call, handlers, defaultHandler)

            if (enableLogging) {
                logException(ex, call)
            }
        }
    }

    private fun logException(ex: Exception, call: ApplicationCall) {
        val path = call.url()
        val method = call.request.httpMethod

        logger.error(ex) {
            "Exception handled on path: $path, http method: $method"
        }
    }
}

