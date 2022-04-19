package com.between_freedom_and_space.mono_backend.common.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Serializable
data class PageParams(

    @SerialName("page_number")
    @Min(value = 1, message = "Page number can't be less than 1")
    val pageNumber: Int,

    @SerialName("page_size")
    @Min(value = 1, message = "Page size can't be less than 1")
    @Max(value = 50, message = "Page size can't be grater than 50")
    val pageSize: Int,
)
