package com.applaudostudios.core.data.mapper

import com.applaudostudios.core.data.dto.CardDTO
import com.applaudostudios.core.data.dto.SeasonDTO
import com.applaudostudios.core.data.dto.ShowDTO
import com.applaudostudios.core.domain.model.Card
import com.applaudostudios.core.domain.model.Season
import com.applaudostudios.core.domain.model.Show
import java.math.BigDecimal
import java.math.RoundingMode

fun CardDTO.mapToCard() =
    Card(
        id,
        name,
        urlImg ?: poster,
        BigDecimal(popularity / 2).setScale(1, RoundingMode.HALF_EVEN).toDouble()
    )

fun List<CardDTO>.mapToCard() = this.map { it.mapToCard() }

fun ShowDTO.mapToShow() = Show(
    id = id,
    name = name,
    urlImg = urlImg ?: poster,
    rating = rating / 2,
    tagline = tagline,
    summary = summary,
    seasons = seasons.mapToSeason()
)

fun SeasonDTO.matToSeason() = Season(id = id, name = name, episodes = episodes, summary = summary, urlImage = urlImage)

fun List<SeasonDTO>.mapToSeason() = this.map { it.matToSeason() }
