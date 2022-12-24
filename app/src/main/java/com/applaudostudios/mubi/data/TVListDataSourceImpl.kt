package com.applaudostudios.mubi.data

import com.applaudostudios.core.data.datasource.TVListDataSource
import com.applaudostudios.core.data.mapper.mapToCard
import com.applaudostudios.core.domain.model.Card
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.TVListType
import com.applaudostudios.mubi.network.TheMovieDBApi
import javax.inject.Inject

class TVListDataSourceImpl @Inject constructor(private val api: TheMovieDBApi) : TVListDataSource {

    override suspend fun getTVList(tvListType: TVListType): Response<List<Card>> {
        return try {
            val result = api.getTVResponse(tvListType.value())
            if (result.results.isNotEmpty())
                Response.Success(result.results.mapToCard())
            else
                Response.Error(Exception(""))
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}