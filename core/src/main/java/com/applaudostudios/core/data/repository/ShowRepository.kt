package com.applaudostudios.core.data.repository

import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.Show

interface ShowRepository {
    suspend fun getShow(id: Int): Response<Show>
}
