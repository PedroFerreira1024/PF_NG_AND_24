package com.ng.challenge.moviesapp.movie_popular.data.repository

import androidx.paging.PagingSource
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.paging.MoviePagingSource
import com.ng.challenge.moviesapp.movie_popular.domain.repository.IMoviePopularRepository
import com.ng.challenge.moviesapp.movie_popular.domain.source.IMoviePopularDataSource

class MoviePopularRepository (
    private val remoteDataSource: IMoviePopularDataSource
): IMoviePopularRepository {

    override fun getPopularMovies(): PagingSource<Int, Movie> {
        return MoviePagingSource(remoteDataSource)
    }
}