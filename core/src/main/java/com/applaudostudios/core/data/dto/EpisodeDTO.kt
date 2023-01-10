package com.applaudostudios.core.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeDTO(
    @Json(name = "id") val id:Int,
    @Json(name = "episode_number") val episodeNumber:Int,
    @Json(name = "name") val name:String,
)