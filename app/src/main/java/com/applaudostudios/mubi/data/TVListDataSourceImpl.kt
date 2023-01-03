package com.applaudostudios.mubi.data

import android.util.Log
import com.applaudostudios.core.data.datasource.TVListDataSource
import com.applaudostudios.core.data.mapper.mapToCard
import com.applaudostudios.core.domain.model.Card
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.TVListType
import com.applaudostudios.mubi.network.TheMovieDBApi
import javax.inject.Inject

class TVListDataSourceImpl @Inject constructor(private val api: TheMovieDBApi) : TVListDataSource {

    private var pages = 0
    private var currentPages = 1
    private var prevTVListType: TVListType? = null

    override suspend fun getTVList(tvListType: TVListType): Response<List<Card>> {
        return try {
            if (tvListType != prevTVListType) pages = 0
            if (pages != 0) currentPages++
            val result = api.getTVResponse(tvListType.value(), page = currentPages)
            pages = result.totalPages
            Log.d("Pages", pages.toString())
            if (result.results.isNotEmpty())
                Response.Success(result.results.mapToCard())
            else
                Response.Error(Exception(""))
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}