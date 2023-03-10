package com.applaudostudios.core.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "backdrop_path") val urlImg: String?,
    @Json(name = "poster_path") val poster: String?,
    @Json(name = "vote_average") val popularity: Double
)