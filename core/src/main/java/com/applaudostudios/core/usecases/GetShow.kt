package com.applaudostudios.core.usecases

import com.applaudostudios.core.data.repository.ShowRepository
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.Show

class GetShow(private val repository: ShowRepository) {
    suspend operator fun invoke(id: Int): Response<Show> = repository.getShow(id)
}