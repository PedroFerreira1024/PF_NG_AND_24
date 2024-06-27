package com.ng.challenge.moviesapp.movie_popular.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.paging.LIMIT
import com.ng.challenge.moviesapp.movie_popular.domain.repository.IMoviePopularRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IGetPopularMoviesUseCase {
    operator fun invoke(): Flow<PagingData<Movie>>
}

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: IMoviePopularRepository
): IGetPopularMoviesUseCase {
    override fun invoke(): Flow<PagingData<Movie>> {
        return repository.getPopularMovies(
            pagingConfig = PagingConfig(
                pageSize = LIMIT,
                initialLoadSize = LIMIT
            )
        )
    }

}