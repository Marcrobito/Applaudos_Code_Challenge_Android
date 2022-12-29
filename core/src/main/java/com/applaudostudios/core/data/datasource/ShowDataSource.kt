package com.applaudostudios.core.data.datasource

import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.Show

interface ShowDataSource {
    suspend fun getShow(id: Int): Response<Show>
}