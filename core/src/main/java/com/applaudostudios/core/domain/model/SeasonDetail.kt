package com.applaudostudios.core.domain.model

data class SeasonDetail(
    val id: Int,
    val name: String,
    val episodes: List<Episode>,
    val image: String?
)
