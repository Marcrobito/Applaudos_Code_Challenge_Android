package com.applaudostudios.mubi

import com.applaudostudios.core.data.repository.TVListRepository
import com.applaudostudios.core.domain.model.Response
import com.applaudostudios.core.usecases.SearchTVShow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.runners.MockitoJUnitRunner


private const val ERROR_QUERY = "error"
private const val SUCCESS_QUERY = "success"


@RunWith(MockitoJUnitRunner::class)
class SearchTVShowTest {

    private lateinit var searchTVShow: SearchTVShow
    private val searchRepositoryMock = mock(TVListRepository::class.java)

    @Before
    fun setup() {
        searchTVShow = SearchTVShow(searchRepositoryMock)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `user queries unknown series test`() = runTest {
        `when`(searchRepositoryMock.searchTVShow(ERROR_QUERY)).thenReturn(
            Response.Error(
                Exception(
                    ERROR_QUERY
                )
            )
        )
        val result = searchTVShow.invoke(ERROR_QUERY)
        assert(result is Response.Error)
        Assert.assertEquals((result as Response.Error).error.message, ERROR_QUERY)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `user queries known series test`() = runTest {
        `when`(searchRepositoryMock.searchTVShow(SUCCESS_QUERY)).thenReturn(
            Response.Success(
                emptyList()
            )
        )
        val result = searchTVShow.invoke(SUCCESS_QUERY)
        assert(result is Response.Success)
        assert((result as Response.Success).data.isEmpty())
    }

}