package com.between_freedom_and_space.mono_backend.popular.api.models

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName

interface PopularMetaInfo {

    @SerialName("date_from")
    val dateFrom: Instant

    @SerialName("date_to")
    val dateTo: Instant
}