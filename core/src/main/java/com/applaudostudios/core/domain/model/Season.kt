package com.applaudostudios.core.domain.model

data class Season(
    val id: Int,
    val seasonNumber: Int,
    val name: String,
    val urlImage: String?,
    val episodes: Int,
    val summary: String
)
