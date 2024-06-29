package com.ng.challenge.moviesapp.detail_movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.domain.model.MovieDetails
import com.ng.challenge.moviesapp.detail_movie.domain.repository.IMovieDetailsRepository
import com.ng.challenge.moviesapp.detail_movie.domain.source.IMovieDetailDataSource
import kotlinx.coroutines.flow.Flow

class MovieDetailsRepository (
    private val remoteDataSource: IMovieDetailDataSource
) : IMovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return remoteDataSource.getMovieDetails(movieId)
    }

    override suspend fun getMovieSimilar(
        movieId: Int,
        pagingConfig: PagingConfig
    ): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getSimilarMoviesPagingSource(movieId = movieId)
            }
        ).flow
    }
}