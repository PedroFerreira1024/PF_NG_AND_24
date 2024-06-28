package com.ng.challenge.moviesapp.search_movie.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ng.challenge.moviesapp.core.domain.model.MovieSearch
import com.ng.challenge.moviesapp.search_movie.domain.repository.IMovieSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IGetMovieSearchUseCase {
    operator fun invoke(params: Params): Flow<PagingData<MovieSearch>>
    data class Params(val query: String)
}
class GetMovieSearchUseCase @Inject constructor(
    private val repository: IMovieSearchRepository
): IGetMovieSearchUseCase {
    override fun invoke(params: IGetMovieSearchUseCase.Params): Flow<PagingData<MovieSearch>> {
        return repository.getSearchedMovies(
            query = params.query,
            pagingConfig = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        )
    }
}