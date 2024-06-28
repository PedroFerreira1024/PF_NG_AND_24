package com.ng.challenge.moviesapp.search_movie.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ng.challenge.moviesapp.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow

interface IMovieSearchRepository {
    fun getSearchedMovies(query : String, pagingConfig: PagingConfig): Flow<PagingData<MovieSearch>>
}