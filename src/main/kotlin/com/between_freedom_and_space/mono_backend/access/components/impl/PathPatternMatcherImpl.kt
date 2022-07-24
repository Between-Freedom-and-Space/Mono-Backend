package com.between_freedom_and_space.mono_backend.access.components.impl

import com.between_freedom_and_space.mono_backend.access.components.PathPatternMatcher
import com.between_freedom_and_space.mono_backend.access.components.models.PathMatchResult

class PathPatternMatcherImpl: PathPatternMatcher {

    override fun pathMatchesPattern(pattern: String, rawPath: String): PathMatchResult {
        val validPattern = adaptPath(pattern)
        val validPath = adaptPath(rawPath)
        val patternParts = validPattern.split("/")
        val pathParts = validPath.split("/")

        if (patternParts.size != pathParts.size) {
            return PathMatchResult.notMatches()
        }

        var strength = 0
        val pathValues = mutableMapOf<String, String>()
        patternParts.forEachIndexed { index, patternPart ->
            if (patternPart.isPathVariable()) {
                val alias = patternPart.getPathAlias()
                pathValues[alias] = pathParts[index]
                strength++
                return@forEachIndexed
            }

            val pathPart = pathParts[index]
            if (pathPart != patternPart) {
                return PathMatchResult.notMatches()
            }
        }

        return PathMatchResult(true, strength, pathValues)
    }

    private fun adaptPath(path: String): String {
        val builder = StringBuilder(path)

        if (builder.contains("?")) {
            val index = builder.indexOf("?")
            builder.deleteRange(index, builder.length)
        }
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

    private fun String.getPathAlias(): String {
        return substring(1, lastIndex)
    }
}