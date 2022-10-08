package com.between_freedom_and_space.mono_backend.popular.api.models

import com.between_freedom_and_space.mono_backend.common.api.PageParams
import com.between_freedom_and_space.mono_backend.popular.service.model.PopularPeriod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPopularRequest(

    @SerialName("popular_period")
    val period: PopularPeriod,

    @SerialName("page_params")
    val pageParams: PageParams,
)
