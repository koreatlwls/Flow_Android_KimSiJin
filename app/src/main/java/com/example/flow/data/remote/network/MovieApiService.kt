package com.example.flow.data.remote.network

import com.example.flow.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("/v1/search/movie.json")
    suspend fun getMovies(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int,
    ): Result<MovieResponse>

}