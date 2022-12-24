package com.applaudostudios.mubi.network

import com.applaudostudios.core.data.responses.TVResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "c0ab4f4064c2ee4aec4985e87413bbd3"

interface TheMovieDBApi {

    @GET("tv/{path}")
    suspend fun getTVResponse(
        @Path("path") path: String,
        @Query("api_key") apiKey: String = API_KEY
    ): TVResponse

}