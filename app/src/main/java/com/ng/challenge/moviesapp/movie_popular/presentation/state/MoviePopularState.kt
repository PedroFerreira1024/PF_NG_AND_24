package com.ng.challenge.moviesapp.movie_popular.presentation.state

import androidx.paging.PagingData
import com.ng.challenge.moviesapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviePopularState(
    val movies: Flow<PagingData<Movie>> = emptyFlow()
)