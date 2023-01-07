package com.example.flow.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flow.data.remote.network.MovieApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieApiService: MovieApiService
) : ViewModel() {

    fun getMovies(){
        viewModelScope.launch {
            movieApiService.getMovies(
                "iron man",
                1,
                1
            )
        }

    }

}