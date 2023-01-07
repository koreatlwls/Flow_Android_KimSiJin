package com.example.flow.data.remote.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.flow.data.remote.network.MovieApiService
import com.example.flow.model.Movie

class MoviePagingSource(
    private val movieApiService: MovieApiService,
    private val query: String,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE_INDEX

        movieApiService.getMovies(
            query,
            PAGE_MAX_SIZE,
            page
        ).onSuccess { movieResponse ->
            val total = movieResponse.total
            val prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(PAGE_MAX_SIZE)
            val nextKey = if (movieResponse.items.isEmpty() || total - 10 < page) null else page.plus(PAGE_MAX_SIZE)

            val movies = movieResponse.items.map { it.toMovie() }

            return LoadResult.Page(movies, prevKey, nextKey)
        }.onFailure { exception ->
            return LoadResult.Error(exception)
        }

        return LoadResult.Error(Exception("something went wrong"))
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(PAGE_MAX_SIZE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(PAGE_MAX_SIZE)
        }
    }

    companion object {
        const val PAGE_MAX_SIZE = 10
        private const val STARTING_PAGE_INDEX = 1
    }

}