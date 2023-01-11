package com.applaudostudios.core.usecases

import com.applaudostudios.core.data.repository.SeasonRepository


class GetSeason(private val repository: SeasonRepository) {
    suspend operator fun invoke(seriesId: Int, seasonNumber: Int) =
        repository.getSeason(seriesId, seasonNumber)
}