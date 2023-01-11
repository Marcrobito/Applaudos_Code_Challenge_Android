package com.applaudostudios.core.data.repository

import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.SeasonDetail

interface SeasonRepository {
    suspend fun getSeason(seriesId:Int, seasonNumber:Int): Response<SeasonDetail>
}