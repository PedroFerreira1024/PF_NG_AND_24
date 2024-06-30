package com.ng.challenge.moviesapp.detail_movie.domain.usecase

import androidx.paging.PagingConfig
import com.google.common.truth.Truth.assertThat
import com.ng.challenge.moviesapp.TestDispatcherRule
import com.ng.challenge.moviesapp.core.model.MovieDetailsFactory
import com.ng.challenge.moviesapp.core.model.MovieFactory
import com.ng.challenge.moviesapp.core.model.PagingSourceMoviesFactory
import com.ng.challenge.moviesapp.core.util.ResultData
import com.ng.challenge.moviesapp.detail_movie.domain.repository.IMovieDetailsRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailsUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieDetailsRepository: IMovieDetailsRepository

    private val movieFactory = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val movieDetailsFactory = MovieDetailsFactory()
        .create(poster = MovieDetailsFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesFactory().create(
        listOf(movieFactory)
    )

    private val getMovieDetailsUseCase by lazy {
        GetMovieDetailsUseCase(movieDetailsRepository)
    }

    @Test
    fun should_return_success_from_resultStatus_when_both_requests_return_success() = runTest {

        //Given
        whenever(movieDetailsRepository.getMovieDetails(movieId = movieFactory.id))
            .thenReturn(movieDetailsFactory)

        whenever(movieDetailsRepository.getMovieSimilar(movieId = movieFactory.id))
            .thenReturn(pagingSourceFake)

        //When
        val result = getMovieDetailsUseCase.invoke(
            IGetMovieDetailsUseCase.Params(
                movieId = movieFactory.id,
                pagingConfig = PagingConfig (
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        verify(movieDetailsRepository).getMovieDetails(movieFactory.id)
        verify(movieDetailsRepository).getMovieSimilar(movieFactory.id)

        assertThat(result).isNotNull()
        assertThat(result is ResultData.Success).isTrue()
    }

    @Test
    fun should_return_error_from_resultStatus_when_details_return_error() = runTest {

        //Given
        val exception = RuntimeException()
        whenever(movieDetailsRepository.getMovieDetails(movieId = movieFactory.id))
            .thenThrow(exception)



        //When
        val result = getMovieDetailsUseCase.invoke(
            IGetMovieDetailsUseCase.Params(
                movieId = movieFactory.id,
                pagingConfig = PagingConfig (
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        verify(movieDetailsRepository).getMovieDetails(movieFactory.id)
        assertThat(result is ResultData.Failure).isTrue()
    }

    @Test
    fun should_return_error_from_resultStatus_when_similar_return_error() = runTest {

        //Given
        val exception = RuntimeException()
        whenever(movieDetailsRepository.getMovieSimilar(movieId = movieFactory.id))
            .thenThrow(exception)



        //When
        val result = getMovieDetailsUseCase.invoke(
            IGetMovieDetailsUseCase.Params(
                movieId = movieFactory.id,
                pagingConfig = PagingConfig (
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        verify(movieDetailsRepository).getMovieSimilar(movieFactory.id)
        assertThat(result is ResultData.Failure).isTrue()
    }
}
