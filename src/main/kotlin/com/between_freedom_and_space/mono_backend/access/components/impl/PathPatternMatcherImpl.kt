package com.between_freedom_and_space.mono_backend.access.components.impl

import com.between_freedom_and_space.mono_backend.access.components.PathPatternMatcher
import com.between_freedom_and_space.mono_backend.access.components.PathPatternMatcher.MatchResult

class PathPatternMatcherImpl: PathPatternMatcher {

    override fun pathMatchesPattern(pattern: String, rawPath: String): MatchResult {
        val validPattern = adaptPath(pattern)
        val validPath = adaptPath(rawPath)
        val patternParts = validPattern.split("/")
            .dropWhile { it.isBlank() }
        val pathParts = validPath.split("/")
            .dropWhile { it.isBlank() }

        if (patternParts.size != pathParts.size) {
            return MatchResult(false, 0)
        }

        var strength = 0
        patternParts.forEachIndexed { index, patternPart ->
            if (patternPart.isPathVariable()) {
                strength++
                return@forEachIndexed
            }

            val pathPart = pathParts[index]
            if (pathPart != patternPart) {
                return MatchResult(false, 0)
            }
        }

        return MatchResult(true, strength)
    }

    private fun adaptPath(path: String): String {
        val builder = StringBuilder(path)
        if (builder.startsWith("/")) {
            builder.deleteAt(0)
        }
        if (builder.endsWith("/")) {
            builder.deleteAt(builder.lastIndex)
        }
        return builder.toString()
    }

    private fun String.isPathVariable(): Boolean {
        return startsWith("{") and endsWith("}")
    }
}