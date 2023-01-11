package com.applaudostudios.mubi.data

import android.util.Log
import com.applaudostudios.core.data.datasource.ShowDataSource
import com.applaudostudios.core.data.mapper.mapToShow
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.Show
import com.applaudostudios.mubi.network.TheMovieDBApi
import javax.inject.Inject

class ShowDataSourceImpl @Inject constructor(private val api: TheMovieDBApi) : ShowDataSource {

    override suspend fun getShow(id: Int): Response<Show> {
        return try {
            val result = api.getShow(id)
            Response.Success(result.mapToShow())
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}