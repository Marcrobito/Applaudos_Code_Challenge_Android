package com.applaudostudios.mubi.di

import android.app.Application
import androidx.room.Room
import com.applaudostudios.core.data.datasource.SeasonDataSource
import com.applaudostudios.core.data.datasource.ShowDataSource
import com.applaudostudios.core.data.datasource.TVListDataSource
import com.applaudostudios.core.data.repository.SeasonRepository
import com.applaudostudios.core.data.repository.ShowRepository
import com.applaudostudios.core.data.repository.TVListRepository
import com.applaudostudios.core.usecases.GetSeason
import com.applaudostudios.core.usecases.GetShow
import com.applaudostudios.core.usecases.GetTVList
import com.applaudostudios.core.usecases.SearchTVShow
import com.applaudostudios.mubi.data.SeasonDataSourceImpl
import com.applaudostudios.mubi.data.SeasonRepositoryImpl
import com.applaudostudios.mubi.data.ShowDataSourceImpl
import com.applaudostudios.mubi.data.ShowRepositoryImpl
import com.applaudostudios.mubi.data.TVLIstRepositoryImpl
import com.applaudostudios.mubi.data.TVListDataSourceImpl
import com.applaudostudios.mubi.network.Network
import com.applaudostudios.mubi.network.TheMovieDBApi
import com.applaudostudios.mubi.room.DataBase
import com.applaudostudios.mubi.room.dao.CardDao
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
    fun provideDB(application: Application) = Room.databaseBuilder(
        application,
        DataBase::class.java, "database"
    ).build()

    @Provides
    @Singleton
    fun provideCardDao(db:DataBase):CardDao = db.cardDao()

    @Provides
    @Singleton
    fun provideTVListDataSource(api: TheMovieDBApi, cardDao: CardDao): TVListDataSource = TVListDataSourceImpl(api, cardDao)

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

    @Provides
    @Singleton
    fun provideSearchTVShow(repository: TVListRepository) = SearchTVShow(repository)

    @Provides
    @Singleton
    fun provideSeasonDataSource(api: TheMovieDBApi): SeasonDataSource = SeasonDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideSeasonRepository(dataSource: SeasonDataSource): SeasonRepository =
        SeasonRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideGetSeason(repository: SeasonRepository) = GetSeason(repository)


}