package com.between_freedom_and_space.mono_backend.auth.components.plugin.config

interface IgnoredPath {

    val path: String

    val isPrefix: Boolean
}