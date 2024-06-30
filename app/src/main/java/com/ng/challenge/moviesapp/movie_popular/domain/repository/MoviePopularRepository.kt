package com.ng.challenge.moviesapp.movie_popular.domain.repository

import androidx.paging.PagingSource
import com.ng.challenge.moviesapp.core.domain.model.Movie

interface IMoviePopularRepository {

    fun getPopularMovies(): PagingSource<Int, Movie>
}