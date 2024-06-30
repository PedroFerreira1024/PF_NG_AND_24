package com.ng.challenge.moviesapp.movie_popular.domain.usecase

import androidx.paging.PagingConfig
import com.google.common.truth.Truth.assertThat
import com.ng.challenge.moviesapp.TestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.ng.challenge.moviesapp.core.model.MovieFactory
import com.ng.challenge.moviesapp.core.model.PagingSourceMoviesFactory
import com.ng.challenge.moviesapp.movie_popular.domain.repository.IMoviePopularRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import javax.annotation.meta.When

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var moviePopularRepository: IMoviePopularRepository

    private val movies = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesFactory().create(
        listOf(movies)
    )

    private val getPopularMoviesUseCase by lazy {
        GetPopularMoviesUseCase(moviePopularRepository)
    }

    @Test
    fun should_validate_flow_paging_data_creation_when_invoke_from_useCase_is_called() = runTest{

        //Given
        whenever(moviePopularRepository.getPopularMovies())
            .thenReturn(pagingSourceFake)

        //When
        val result = getPopularMoviesUseCase.invoke(
            params = IGetPopularMoviesUseCase.Params(
                PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        verify(moviePopularRepository).getPopularMovies()
        assertThat(result).isNotNull()
    }

    @Test
    fun should_emit_an_empty_stream_when_exception_is_thrown_when_calling_invoke_method() = runTest {

        //Given
        val exception = RuntimeException()
        whenever(moviePopularRepository.getPopularMovies())
            .thenThrow(exception)

        //When
        val result = getPopularMoviesUseCase.invoke(
            params = IGetPopularMoviesUseCase.Params(
                PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        val resultList = result.toList()
        verify(moviePopularRepository).getPopularMovies()
        assertThat(resultList).isEmpty()
    }
}