package com.applaudostudios.core.data.datasource

import com.applaudostudios.core.domain.model.Card
import com.applaudostudios.core.domain.model.TVListType

interface TVListDataSource {
    suspend fun getTVList(tvListType: TVListType): List<Card>
}