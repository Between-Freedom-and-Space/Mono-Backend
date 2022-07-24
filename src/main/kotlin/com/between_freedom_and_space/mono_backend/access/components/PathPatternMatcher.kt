package com.between_freedom_and_space.mono_backend.access.components

import com.between_freedom_and_space.mono_backend.access.components.models.PathMatchResult

interface PathPatternMatcher {

    fun pathMatchesPattern(pattern: String, rawPath: String): PathMatchResult
}