package com.ng.challenge.moviesapp.detail_movie.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import coil.network.HttpException
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.domain.model.MovieDetails
import com.ng.challenge.moviesapp.core.util.ResultData
import com.ng.challenge.moviesapp.detail_movie.domain.repository.IMovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

interface IGetMovieDetailsUseCase {
    operator fun invoke(params: Params): Flow<ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>>>
    data class Params(val movieId: Int)
}
class GetMovieDetailsUseCase @Inject constructor (
    private val repository: IMovieDetailsRepository
) : IGetMovieDetailsUseCase{
    override fun invoke(params: IGetMovieDetailsUseCase.Params): Flow<ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>>> {
        return flow {
            try {
                emit(ResultData.Loading)
                val movieDetails = repository.getMovieDetails(params.movieId)
                val moviesSimilar = repository.getMovieSimilar(
                    movieId = params.movieId,
                    pagingConfig = PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
                emit(ResultData.Success(moviesSimilar to movieDetails))
            } catch (exception: IOException) {
                emit(ResultData.Failure(exception))
            } catch (exception: HttpException) {
                emit(ResultData.Failure(exception))
            }
        }.flowOn(Dispatchers.IO)
    }
}