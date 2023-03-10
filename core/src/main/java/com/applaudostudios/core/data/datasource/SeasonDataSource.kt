package com.applaudostudios.core.data.datasource

import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.SeasonDetail

interface SeasonDataSource {
    suspend fun getSeason(seriesId:Int, seasonNumber:Int): Response<SeasonDetail>
}