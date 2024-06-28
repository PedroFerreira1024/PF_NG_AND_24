package com.ng.challenge.moviesapp.search_movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ng.challenge.moviesapp.core.domain.model.MovieSearch
import com.ng.challenge.moviesapp.search_movie.domain.repository.IMovieSearchRepository
import com.ng.challenge.moviesapp.search_movie.domain.source.IMovieSearchDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieSearchRepository @Inject constructor(
    private val remoteDataSource: IMovieSearchDataSource
): IMovieSearchRepository {
    override fun getSearchedMovies(
        query: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<MovieSearch>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { remoteDataSource.getSearchMoviePagingSource(query = query)}
        ).flow
    }
}