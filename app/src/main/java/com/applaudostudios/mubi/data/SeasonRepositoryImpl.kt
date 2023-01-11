package com.applaudostudios.mubi.data

import com.applaudostudios.core.data.datasource.SeasonDataSource
import com.applaudostudios.core.data.repository.SeasonRepository

class SeasonRepositoryImpl(private val dataSource: SeasonDataSource):SeasonRepository {
    override suspend fun getSeason(seriesId: Int, seasonNumber: Int) = dataSource.getSeason(seriesId, seasonNumber)
}