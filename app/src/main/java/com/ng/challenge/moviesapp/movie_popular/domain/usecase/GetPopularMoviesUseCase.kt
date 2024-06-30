package com.ng.challenge.moviesapp.movie_popular.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.movie_popular.domain.repository.IMoviePopularRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

interface IGetPopularMoviesUseCase {
    operator fun invoke(params: Params): Flow<PagingData<Movie>>
    data class Params(val pagingConfig: PagingConfig)
}

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: IMoviePopularRepository
) : IGetPopularMoviesUseCase {
    override fun invoke(params: IGetPopularMoviesUseCase.Params): Flow<PagingData<Movie>> {
        return try {
            val pagingSource = repository.getPopularMovies()
            Pager(
                config = params.pagingConfig,
                pagingSourceFactory = {
                    pagingSource
                }
            ).flow
        } catch(e: Exception) {
            emptyFlow()
        }
    }


}