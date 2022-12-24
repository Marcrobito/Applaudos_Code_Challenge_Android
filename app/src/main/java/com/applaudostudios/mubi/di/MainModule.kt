package com.applaudostudios.mubi.di

import com.applaudostudios.core.data.datasource.TVListDataSource
import com.applaudostudios.core.data.repository.TVListRepository
import com.applaudostudios.core.usecases.GetTVList
import com.applaudostudios.mubi.data.TVLIstRepositoryImpl
import com.applaudostudios.mubi.data.TVListDataSourceImpl
import com.applaudostudios.mubi.network.Network
import com.applaudostudios.mubi.network.TheMovieDBApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideApi(): TheMovieDBApi = Network.service

    @Provides
    @Singleton
    fun provideTVListDataSource(api: TheMovieDBApi): TVListDataSource = TVListDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideTVListRepository(dataSource: TVListDataSource): TVListRepository =
        TVLIstRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideGetTVList(repository: TVListRepository): GetTVList = GetTVList(repository)
}