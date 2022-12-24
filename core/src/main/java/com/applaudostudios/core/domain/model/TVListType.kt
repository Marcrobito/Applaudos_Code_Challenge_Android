package com.applaudostudios.core.domain.model

enum class TVListType {
    AIRING_TODAY,
    ON_THE_AIR,
    POPULAR,
    TOP_RATED;

    val value = {
        when (this) {
            AIRING_TODAY -> "airing_today"
            ON_THE_AIR -> "on_the_air"
            POPULAR -> "popular"
            TOP_RATED -> "top_rated"
        }
    }
}