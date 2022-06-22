package com.between_freedom_and_space.mono_backend.access.components.impl

import com.between_freedom_and_space.mono_backend.access.components.PathPatternMatcher

class PathPatternMatcherImpl: PathPatternMatcher {

    override fun pathMatchesPattern(pattern: String, rawPath: String): Boolean {
        val validPattern = adaptPath(pattern)
        val validPath = adaptPath(rawPath)
        val patternParts = validPattern.split("/")
        val pathParts = validPath.split("/")

        if (patternParts.size != pathParts.size) {
            return false
        }

        patternParts.forEachIndexed { index, patternPart ->
            if (patternPart.isPathVariable()) {
                return@forEachIndexed
            }

            val pathPart = pathParts[index]
            if (pathPart != patternPart) {
                return false
            }
        }

        return true
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