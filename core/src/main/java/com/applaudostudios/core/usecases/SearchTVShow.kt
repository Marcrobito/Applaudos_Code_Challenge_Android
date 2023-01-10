package com.applaudostudios.core.usecases

import com.applaudostudios.core.data.repository.TVListRepository

class SearchTVShow(private val repository: TVListRepository) {
    suspend operator fun invoke(query: String) = repository.searchTVShow(query)
}