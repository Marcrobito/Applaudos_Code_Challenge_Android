package com.applaudostudios.mubi.data

import com.applaudostudios.core.data.datasource.ShowDataSource
import com.applaudostudios.core.data.repository.ShowRepository
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.domain.model.Show
import javax.inject.Inject

class ShowRepositoryImpl @Inject constructor(private val dataSource: ShowDataSource) :
    ShowRepository {
    override suspend fun getShow(id: Int): Response<Show> = dataSource.getShow(id)
}