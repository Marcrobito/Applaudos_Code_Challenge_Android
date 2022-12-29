package com.applaudostudios.core.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "backdrop_path") val urlImg: String?,
    @Json(name = "poster_path") val poster: String,
    @Json(name = "vote_average") val rating: Double,
    @Json(name = "tagline") val tagline: String,
    @Json(name = "overview") val summary: String,
    @Json(name = "seasons") val seasons:List<SeasonDTO>
)