package com.ng.challenge.moviesapp.search_movie.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ng.challenge.moviesapp.search_movie.domain.usecase.GetMovieSearchUseCase
import com.ng.challenge.moviesapp.search_movie.domain.usecase.IGetMovieSearchUseCase
import com.ng.challenge.moviesapp.search_movie.presentation.state.MovieSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor (
    private val getMovieSearchUseCase: GetMovieSearchUseCase
): ViewModel() {

    var uiState by mutableStateOf(MovieSearchState())
        private set

    fun fetch(query: String = "") {
        val movies = getMovieSearchUseCase.invoke(
            params = IGetMovieSearchUseCase.Params(
                query = query
            )
        ).cachedIn(viewModelScope)
        uiState = uiState.copy(movies = movies)
    }

    fun event(event: MovieSearchEvent) {
        uiState = when(event) {
            is MovieSearchEvent.EnteredQuery -> {
                uiState.copy(query = event.value)
            }
        }
    }
}