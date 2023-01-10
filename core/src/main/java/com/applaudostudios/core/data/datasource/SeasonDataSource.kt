package com.applaudostudios.core.data.datasource

import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.Season

interface SeasonDataSource {
    suspend fun getSeason(id: Int): Response<Season>
}