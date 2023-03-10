package com.example.flow.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.flow.data.repository.MovieRepository
import com.example.flow.data.repository.SearchRecordRepository
import com.example.flow.model.Movie
import com.example.flow.model.SearchRecord
import com.example.flow.util.MutableEventFlow
import com.example.flow.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val searchRecordRepository: SearchRecordRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Error)
    val uiState: StateFlow<UiState>
        get() = _uiState

    private val _query = MutableEventFlow<String>()
    val query = _query.asEventFlow()
    private var currentQuery = ""

    private val _movieResponse = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val movieResponse: StateFlow<PagingData<Movie>>
        get() = _movieResponse

    val searchRecords: StateFlow<List<SearchRecord>> = searchRecordRepository.getAllSearchRecords().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    private val _clickSearchRecord = MutableEventFlow<String>()
    val clickSearchRecord = _clickSearchRecord.asEventFlow()

    fun setQuery(input: String) {
        viewModelScope.launch {
            if (currentQuery != input) {
                _query.emit(input)
                currentQuery = input
                searchRecordRepository.insertSearchRecord(SearchRecord(input))
            }
        }
    }

    fun getMovies(query: String) {
        viewModelScope.launch {
            movieRepository.getMovies(query)
                .cachedIn(viewModelScope)
                .collectLatest {
                    _movieResponse.value = it
                }
        }
    }

    fun retryGetMovies() {
        viewModelScope.launch {
            movieRepository.getMovies(currentQuery)
                .cachedIn(viewModelScope)
                .collectLatest {
                    _movieResponse.value = it
                }
        }
    }

    fun setLoadingState() {
        _uiState.value = UiState.Loading
    }

    fun setSuccessState() {
        _uiState.value = UiState.Success
    }

    fun setErrorState() {
        _uiState.value = UiState.Error
    }

    fun setEmptyState() {
        _uiState.value = UiState.Empty
    }

    fun deleteAllSearchRecords() {
        viewModelScope.launch {
            searchRecordRepository.deleteAllSearchRecords()
        }
    }

    fun setClickSearchRecord(input : String){
        viewModelScope.launch {
            _clickSearchRecord.emit(input)
        }
    }

}

sealed class UiState {
    object Loading : UiState()
    object Empty : UiState()
    object Success : UiState()
    object Error : UiState()
}