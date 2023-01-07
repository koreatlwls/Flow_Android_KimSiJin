package com.example.flow.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.flow.data.repository.MovieRepository
import com.example.flow.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieResponse = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val movieResponse: StateFlow<PagingData<Movie>> = _movieResponse

    fun getMovies(query: String) {
        viewModelScope.launch {
            movieRepository.getMovies(query)
                .cachedIn(viewModelScope)
                .collectLatest {
                    _movieResponse.value = it
                }
        }
    }

}