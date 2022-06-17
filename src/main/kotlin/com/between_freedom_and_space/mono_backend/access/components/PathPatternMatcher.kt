package com.between_freedom_and_space.mono_backend.access.components

interface PathPatternMatcher {

    fun pathMatchesPattern(pattern: String, rawPath: String): Boolean
}