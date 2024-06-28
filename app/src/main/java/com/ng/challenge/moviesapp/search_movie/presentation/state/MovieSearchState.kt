package com.ng.challenge.moviesapp.search_movie.presentation.state

import androidx.paging.PagingData
import com.ng.challenge.moviesapp.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieSearchState(
    val query: String = "",
    val movies: Flow<PagingData<MovieSearch>> = emptyFlow()
)