package com.ng.challenge.moviesapp.movie_popular.domain.source

import com.ng.challenge.moviesapp.core.domain.model.MoviePaging
import com.ng.challenge.moviesapp.core.paging.MoviePagingSource

interface IMoviePopularDataSource {

    fun getPopularMoviesPagingSource(): MoviePagingSource
    suspend fun getPopularMovies(page: Int): MoviePaging
}