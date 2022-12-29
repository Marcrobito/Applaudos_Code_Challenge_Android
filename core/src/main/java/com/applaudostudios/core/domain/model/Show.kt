package com.applaudostudios.core.domain.model

data class Show(
    val id: Int,
    val name: String,
    val urlImg: String,
    val rating: Double,
    val tagline: String,
    val summary: String,
    val seasons:List<Season>
)