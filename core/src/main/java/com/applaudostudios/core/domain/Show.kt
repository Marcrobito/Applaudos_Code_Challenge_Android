package com.applaudostudios.core.domain

data class Show(
    val name: String,
    val urlImg: String,
    val rating: Float,
    val title: String,
    val summary: String,
    val seasons:List<Season>
)