package com.ng.challenge.moviesapp.detail_movie.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface IMovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): MovieDetails
    fun getMovieSimilar(movieId: Int): PagingSource<Int, Movie>
}