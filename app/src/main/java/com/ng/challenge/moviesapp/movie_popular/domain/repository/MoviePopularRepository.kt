package com.ng.challenge.moviesapp.movie_popular.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ng.challenge.moviesapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMoviePopularRepository {

    fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}