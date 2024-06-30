package com.ng.challenge.moviesapp.detail_movie.data.repository

import androidx.paging.PagingSource
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.domain.model.MovieDetails
import com.ng.challenge.moviesapp.core.paging.MovieSimilarPagingSource
import com.ng.challenge.moviesapp.detail_movie.domain.repository.IMovieDetailsRepository
import com.ng.challenge.moviesapp.detail_movie.domain.source.IMovieDetailDataSource
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(
    private val remoteDataSource: IMovieDetailDataSource
) : IMovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return remoteDataSource.getMovieDetails(movieId = movieId)
    }

    override fun getMovieSimilar(
        movieId: Int
    ): PagingSource<Int,Movie> {
        return MovieSimilarPagingSource(movieId = movieId, remoteDataSource = remoteDataSource)
    }
}