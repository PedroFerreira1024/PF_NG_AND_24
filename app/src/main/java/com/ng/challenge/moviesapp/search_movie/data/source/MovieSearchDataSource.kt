package com.ng.challenge.moviesapp.search_movie.data.source

import com.ng.challenge.moviesapp.core.data.remote.MovieService
import com.ng.challenge.moviesapp.core.data.remote.response.SearchResponse
import com.ng.challenge.moviesapp.core.paging.MovieSearchPagingSource
import com.ng.challenge.moviesapp.search_movie.domain.source.IMovieSearchDataSource
import javax.inject.Inject

class MovieSearchDataSource @Inject constructor(
    private val service: MovieService
) : IMovieSearchDataSource {
    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(query = query, remoteDataSource = this)
    }

    override suspend fun getSearchMovies(page: Int, query: String): SearchResponse {
        return service.searchMovie(page = page, query = query)
    }
}