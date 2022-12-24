package com.applaudostudios.core.data.repository

import com.applaudostudios.core.data.datasource.TVListDataSource
import com.applaudostudios.core.domain.model.TVListType

class TVListRepository(private val dataSource: TVListDataSource) {
    suspend fun getTVList(tvListType: TVListType) = dataSource.getTVList(tvListType)
}