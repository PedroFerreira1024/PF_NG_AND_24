package com.ng.challenge.moviesapp.search_movie.domain.usecase

import androidx.paging.PagingConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth.assertThat
import com.ng.challenge.moviesapp.TestDispatcherRule
import com.ng.challenge.moviesapp.core.model.MovieFactory
import com.ng.challenge.moviesapp.core.model.PagingSourceMoviesFactory
import com.ng.challenge.moviesapp.search_movie.data.repository.MovieSearchRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieSearchUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieSearchRepository: MovieSearchRepository

    private val movieSearchFactory = MovieFactory()
        .create(poster = MovieFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesFactory().create(
        listOf(movieSearchFactory)
    )

    private val getSearchMoviesUseCase by lazy {
        GetMovieSearchUseCase(movieSearchRepository)
    }

    @Test
    fun should_validate_flow_paging_data_creation_when_invoke_from_useCase_is_called() = runTest{

        //Given
        whenever(movieSearchRepository.getSearchedMovies(query = ""))
            .thenReturn(pagingSourceFake)

        //When
        val result = getSearchMoviesUseCase.invoke(
            params = IGetMovieSearchUseCase.Params(
                query = "",
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        ).first()

        verify(movieSearchRepository).getSearchedMovies(query = "")
        assertThat(result).isNotNull()
    }

    @Test
    fun should_emit_an_empty_stream_when_exception_is_thrown_when_calling_invoke_method() = runTest {

        //Given
        val exception = RuntimeException()
        whenever(movieSearchRepository.getSearchedMovies(""))
            .thenThrow(exception)

        //When
        val result = getSearchMoviesUseCase.invoke(
            params = IGetMovieSearchUseCase.Params(
                query = "",
                PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        val resultList = result.toList()
        verify(movieSearchRepository).getSearchedMovies(query = "")
        assertThat(resultList).isEmpty()
    }
}