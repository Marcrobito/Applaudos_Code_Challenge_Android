package com.applaudostudios.core.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeasonDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "season_number") val seasonNumber: Int,
    @Json(name = "name") val name: String,
    @Json(name = "poster_path") val urlImage:String?,
    @Json(name = "episode_count") val episodes: Int,
    @Json(name = "overview") val summary: String
)