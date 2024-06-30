package com.ng.challenge.moviesapp.search_movie.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.search_movie.domain.repository.IMovieSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

interface IGetMovieSearchUseCase {
    operator fun invoke(params: Params): Flow<PagingData<Movie>>
    data class Params(val query: String, val pagingConfig: PagingConfig)
}
class GetMovieSearchUseCase @Inject constructor(
    private val repository: IMovieSearchRepository
): IGetMovieSearchUseCase {
    override fun invoke(params: IGetMovieSearchUseCase.Params): Flow<PagingData<Movie>> {
        return try {
            val pagingSource = repository.getSearchedMovies(params.query)
            return Pager(
                config = params.pagingConfig,
                pagingSourceFactory = {
                    pagingSource
                }
            ).flow
        } catch (e: Exception) {
            emptyFlow()
        }
    }
}