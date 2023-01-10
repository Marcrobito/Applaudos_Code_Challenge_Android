package com.applaudostudios.core.data.dto

import com.applaudostudios.core.domain.model.Episode
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeasonDetailDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "episodes") val episodes: List<Episode>,
    @Json(name = "poster_path") val image: String?
)