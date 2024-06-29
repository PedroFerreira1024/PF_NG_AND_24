package com.ng.challenge.moviesapp.movie_popular.data.source

import com.ng.challenge.moviesapp.core.data.remote.MovieService
import com.ng.challenge.moviesapp.core.data.remote.response.MovieResponse
import com.ng.challenge.moviesapp.core.paging.MoviePagingSource
import com.ng.challenge.moviesapp.movie_popular.domain.source.IMoviePopularDataSource


class MoviePopularDataSource (
    private val service: MovieService
) : IMoviePopularDataSource {

    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }
    override suspend fun getPopularMovies(page: Int): MovieResponse {
        return service.getPopularMovies(page = page)
    }
}