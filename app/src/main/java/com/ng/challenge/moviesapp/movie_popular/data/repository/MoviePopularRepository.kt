package com.ng.challenge.moviesapp.movie_popular.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.paging.MoviePagingSource
import com.ng.challenge.moviesapp.movie_popular.domain.repository.IMoviePopularRepository
import com.ng.challenge.moviesapp.movie_popular.domain.source.IMoviePopularDataSource
import kotlinx.coroutines.flow.Flow

class MoviePopularRepository (
    private val remoteDataSource: IMoviePopularDataSource
): IMoviePopularRepository {

    override fun getPopularMovies(): PagingSource<Int, Movie> {
        return MoviePagingSource(remoteDataSource)
    }
}