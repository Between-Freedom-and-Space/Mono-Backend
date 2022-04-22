package com.between_freedom_and_space.mono_backend.posts.api.models

import com.between_freedom_and_space.mono_backend.posts.internal.reactions.entities.Reaction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReactPostRequest(

    @SerialName("react_alias")
    val reactAlias: Reaction,
)
