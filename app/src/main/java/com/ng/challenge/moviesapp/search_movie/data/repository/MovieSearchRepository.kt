package com.ng.challenge.moviesapp.search_movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.paging.MovieSearchPagingSource
import com.ng.challenge.moviesapp.search_movie.domain.repository.IMovieSearchRepository
import com.ng.challenge.moviesapp.search_movie.domain.source.IMovieSearchDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieSearchRepository @Inject constructor(
    private val remoteDataSource: IMovieSearchDataSource
): IMovieSearchRepository {
    override fun getSearchedMovies(
        query: String
    ): PagingSource<Int, Movie> {
        return MovieSearchPagingSource(query, remoteDataSource)
    }
}