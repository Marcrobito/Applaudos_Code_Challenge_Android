package com.applaudostudios.mubi.data

import com.applaudostudios.core.data.datasource.TVListDataSource
import com.applaudostudios.core.data.repository.TVListRepository
import com.applaudostudios.core.domain.model.TVListType
import javax.inject.Inject

class TVLIstRepositoryImpl @Inject constructor(private val dataSource: TVListDataSource) :
    TVListRepository {
    override suspend fun getTVList(tvListType: TVListType) = dataSource.getTVList(tvListType)
    override suspend fun searchTVShow(query: String) = dataSource.searchShow(query)
}