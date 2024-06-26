package com.ng.challenge.moviesapp.detail_movie.domain.source

import com.ng.challenge.moviesapp.core.domain.model.MovieDetails
import com.ng.challenge.moviesapp.core.domain.model.MoviePaging
import com.ng.challenge.moviesapp.core.paging.MovieSimilarPagingSource

interface IMovieDetailDataSource {
    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun getMovieSimilar(page: Int, movieId: Int): MoviePaging

    fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource
}