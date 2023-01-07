package com.example.flow.data.repository

import androidx.paging.PagingData
import com.example.flow.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(
        query: String
    ): Flow<PagingData<Movie>>

}