package com.applaudostudios.core.data.responses

import com.applaudostudios.core.data.dto.CardDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TVResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<CardDTO>,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int
)
