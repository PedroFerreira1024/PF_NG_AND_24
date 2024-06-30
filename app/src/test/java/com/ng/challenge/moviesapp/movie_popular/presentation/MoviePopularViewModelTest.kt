package com.ng.challenge.moviesapp.movie_popular.presentation


import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import com.ng.challenge.moviesapp.TestDispatcherRule
import com.ng.challenge.moviesapp.core.model.MovieFactory
import com.ng.challenge.moviesapp.movie_popular.domain.usecase.IGetPopularMoviesUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviePopularViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getPopularMoviesUseCase: IGetPopularMoviesUseCase

    private val viewModel by lazy {
        MoviePopularViewModel(getPopularMoviesUseCase)
    }

    private val fakePagingDataMovies = PagingData.from(
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.InsideOut),
        )
    )
    @Test
    fun must_validate_paging_data_object_values_when_calling_paging_data_from_movies() = runTest {

        //Given
        whenever(getPopularMoviesUseCase.invoke(any())).thenReturn(
            flowOf(fakePagingDataMovies)
        )

        //When
        val result = viewModel.uiState.movies.first()

        //Then
        assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun must_throw_an_exception_when_the_calling_to_the_use_case_returns_an_exception () = runTest {

        //Given
        whenever(getPopularMoviesUseCase.invoke(any()))
            .thenThrow(RuntimeException())

        //When
        val result = viewModel.uiState.movies.first()

        //Then
        assertThat(result).isNull() //probably unused
    }
}