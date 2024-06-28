package com.ng.challenge.moviesapp.search_movie.domain.source

import com.ng.challenge.moviesapp.core.data.remote.response.SearchResponse
import com.ng.challenge.moviesapp.core.paging.MovieSearchPagingSource

interface IMovieSearchDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource
    suspend fun getSearchMovies(page: Int, query: String): SearchResponse
}