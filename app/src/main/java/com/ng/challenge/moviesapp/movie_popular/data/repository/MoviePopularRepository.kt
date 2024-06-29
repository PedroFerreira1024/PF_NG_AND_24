package com.ng.challenge.moviesapp.movie_popular.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.movie_popular.domain.repository.IMoviePopularRepository
import com.ng.challenge.moviesapp.movie_popular.domain.source.IMoviePopularDataSource
import kotlinx.coroutines.flow.Flow

class MoviePopularRepository (
    private val remoteDataSource: IMoviePopularDataSource
): IMoviePopularRepository {

    override fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getPopularMoviesPagingSource()
            }
        ).flow
    }
}