package com.applaudostudios.core.data.repository

import com.applaudostudios.core.domain.model.Card
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.TVListType

interface TVListRepository {
    suspend fun getTVList(tvListType: TVListType):Response<List<Card>>
}