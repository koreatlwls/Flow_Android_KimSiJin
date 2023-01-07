package com.example.flow.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.flow.data.remote.network.MovieApiService
import com.example.flow.data.remote.source.MoviePagingSource
import com.example.flow.data.remote.source.MoviePagingSource.Companion.PAGE_MAX_SIZE
import com.example.flow.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService
) : MovieRepository {

    override fun getMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_MAX_SIZE,
                enablePlaceholders = false,
                prefetchDistance = 1,
            ),
            pagingSourceFactory = {
                MoviePagingSource(
                    movieApiService,
                    query
                )
            }
        ).flow
    }

}