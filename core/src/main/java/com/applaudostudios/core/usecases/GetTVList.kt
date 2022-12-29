package com.applaudostudios.core.usecases

import com.applaudostudios.core.data.repository.TVListRepository
import com.applaudostudios.core.domain.model.Card
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.TVListType
import com.applaudostudios.core.domain.model.TVListType.TOP_RATED

class GetTVList(private val repository: TVListRepository) {
    suspend operator fun invoke(tvListType: TVListType = TOP_RATED): Response<List<Card>> =
        repository.getTVList(tvListType)
}