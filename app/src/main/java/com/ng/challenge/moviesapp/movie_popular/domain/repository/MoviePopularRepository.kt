package com.ng.challenge.moviesapp.movie_popular.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.ng.challenge.moviesapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMoviePopularRepository {

    fun getPopularMovies(): PagingSource<Int, Movie>
}