package com.applaudostudios.mubi.data

import com.applaudostudios.core.data.datasource.SeasonDataSource
import com.applaudostudios.core.data.mapper.mapToSeasonDetail
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.SeasonDetail
import com.applaudostudios.mubi.network.TheMovieDBApi
import javax.inject.Inject

class SeasonDataSourceImpl@Inject constructor(private val api: TheMovieDBApi) : SeasonDataSource{
    override suspend fun getSeason(seriesId: Int, seasonNumber: Int): Response<SeasonDetail> {
        return try {
            Response.Success(api.getSeason(seriesId, seasonNumber).mapToSeasonDetail())
        }catch (e:Exception){
            Response.Error(e)
        }
    }
}