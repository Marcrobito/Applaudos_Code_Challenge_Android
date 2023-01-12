package com.applaudostudios.mubi.data

import android.util.Log
import com.applaudostudios.core.data.datasource.TVListDataSource
import com.applaudostudios.core.data.mapper.mapToCard
import com.applaudostudios.core.domain.model.Card
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.TVListType
import com.applaudostudios.mubi.network.TheMovieDBApi
import com.applaudostudios.mubi.room.dao.CardDao
import com.applaudostudios.mubi.room.toCardEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TVListDataSourceImpl @Inject constructor(
    private val api: TheMovieDBApi,
    private val cardDao: CardDao
) : TVListDataSource {

    private var pages = 0
    private var currentPages = 1
    private var prevTVListType: TVListType? = null
    private var prevSearch: String? = null

    override suspend fun getTVList(tvListType: TVListType): Response<List<Card>> {
        return try {
            if (tvListType != prevTVListType) {
                pages = 0
                currentPages = 1
            }
            prevTVListType = tvListType
            if (pages != 0) currentPages++
            val result = api.getTVResponse(tvListType.value(), page = currentPages)
            pages = result.totalPages
            if (result.results.isNotEmpty()) {
                result.results.forEach { cardDTO ->
                    withContext(Dispatchers.IO){
                        val card = cardDao.findById(cardDTO.id)
                        if (card == null) {
                            cardDao.insertAll(cardDTO.toCardEntity())
                        }
                    }
                }
                Response.Success(result.results.mapToCard())
            } else
                Response.Error(Exception("Empty Result"))
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun searchShow(query: String): Response<List<Card>> {
        return try {
            if (prevSearch != query) {
                pages = 0
                currentPages = 1
            }
            prevSearch = query
            if (pages != 0) currentPages++
            val result = api.searchShow(query, page = currentPages)
            pages = result.totalPages
            if (result.results.isNotEmpty())
                Response.Success(result.results.mapToCard())
            else
                Response.Error(Exception(""))
        } catch (e: Exception) {
            Response.Error(e)
        }
    }


}