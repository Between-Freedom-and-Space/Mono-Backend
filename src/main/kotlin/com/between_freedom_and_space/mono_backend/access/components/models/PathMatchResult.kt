package com.between_freedom_and_space.mono_backend.access.components.models

data class PathMatchResult(

    val match: Boolean,

    val strength: Int,

    val pathTokens: Map<String, String>
) {

    companion object {

        fun notMatches(): PathMatchResult {
            return PathMatchResult(false, 0, emptyMap())
        }
    }
}
