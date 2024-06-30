package com.ng.challenge.moviesapp.search_movie.domain.repository

import androidx.paging.PagingSource
import com.ng.challenge.moviesapp.core.domain.model.Movie

interface IMovieSearchRepository {
    fun getSearchedMovies(query : String): PagingSource<Int, Movie>
}