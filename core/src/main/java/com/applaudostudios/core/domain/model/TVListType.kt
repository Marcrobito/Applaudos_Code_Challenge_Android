package com.applaudostudios.core.domain.model

enum class TVListType {

    TOP_RATED, POPULAR, ON_THE_AIR, AIRING_TODAY;

    val value: () -> String = {
        when (this) {
            AIRING_TODAY -> "airing_today"
            ON_THE_AIR -> "on_the_air"
            POPULAR -> "popular"
            TOP_RATED -> "top_rated"
        }
    }

    val title: () -> String = {
        when (this) {
            AIRING_TODAY -> "Airing Today"
            ON_THE_AIR -> "On TV"
            POPULAR -> "Popular"
            TOP_RATED -> "Top Rated"
        }
    }
}