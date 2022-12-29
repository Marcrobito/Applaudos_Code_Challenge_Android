package com.applaudostudios.mubi.di

import com.applaudostudios.core.data.datasource.ShowDataSource
import com.applaudostudios.core.data.datasource.TVListDataSource
import com.applaudostudios.core.data.repository.ShowRepository
import com.applaudostudios.core.data.repository.TVListRepository
import com.applaudostudios.core.usecases.GetShow
import com.applaudostudios.core.usecases.GetTVList
import com.applaudostudios.mubi.data.ShowDataSourceImpl
import com.applaudostudios.mubi.data.ShowRepositoryImpl
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
    fun provideGetTVList(repository: TVListRepository) = GetTVList(repository)

    @Provides
    @Singleton
    fun provideShowDataSource(api: TheMovieDBApi): ShowDataSource = ShowDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideShowRepository(dataSource: ShowDataSource): ShowRepository =
        ShowRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideGetShow(repository: ShowRepository) = GetShow(repository)
}