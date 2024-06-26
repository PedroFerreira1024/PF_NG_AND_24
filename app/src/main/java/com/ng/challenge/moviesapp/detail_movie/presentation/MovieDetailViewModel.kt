package com.ng.challenge.moviesapp.detail_movie.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import com.ng.challenge.moviesapp.core.util.Constants
import com.ng.challenge.moviesapp.core.util.ResultData
import com.ng.challenge.moviesapp.core.util.UtilFunctions
import com.ng.challenge.moviesapp.detail_movie.domain.usecase.IGetMovieDetailsUseCase
import com.ng.challenge.moviesapp.detail_movie.presentation.state.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: IGetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf(MovieDetailState())
        private set

    private val movieId = savedStateHandle.get<Int>(key = Constants.MOVIE_DETAIL_ARGUMENT)

    init {
        movieId?.let { safeMovieId ->
            getMovieDetail(MovieDetailEvent.GetMovieDetail(safeMovieId))
        }
    }

    private fun getMovieDetail(getMovieDetail: MovieDetailEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    private fun event(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.GetMovieDetail ->
                viewModelScope.launch {
                    val resultData = getMovieDetailsUseCase.invoke(
                        params = IGetMovieDetailsUseCase.Params(
                            movieId = event.movieId,
                            pagingConfig = pagingConfig()
                        )
                    )
                    when (resultData) {
                        is ResultData.Success -> {
                            uiState = uiState.copy(
                                isLoading = false,
                                movieDetails = resultData.data?.second,
                                results = resultData.data?.first ?: emptyFlow()
                            )
                        }

                        is ResultData.Failure -> {
                            UtilFunctions.logError(
                                "Movie Detail ERROR",
                                resultData.e.message.toString()
                            )
                        }

                        is ResultData.Loading -> {
                            uiState = uiState.copy(
                                isLoading = true
                            )
                        }
                    }
                }
        }
    }
}

private fun pagingConfig(): PagingConfig {
    return PagingConfig(
        pageSize = 20,
        initialLoadSize = 20
    )
}