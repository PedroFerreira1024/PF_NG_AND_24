package com.ng.challenge.moviesapp.detail_movie.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import com.ng.challenge.moviesapp.TestDispatcherRule
import com.ng.challenge.moviesapp.core.util.ResultData
import com.ng.challenge.moviesapp.detail_movie.domain.usecase.IGetMovieDetailsUseCase
import com.ng.challenge.moviesapp.core.model.MovieDetailsFactory
import com.ng.challenge.moviesapp.core.model.MovieFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsViewModelTest {

    @get:Rule
    val dispatcher = TestDispatcherRule()

    @Mock
    lateinit var getMovieDetailsUseCase: IGetMovieDetailsUseCase

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    private val movieDetailsFactory =
        MovieDetailsFactory().create(poster = MovieDetailsFactory.Poster.Avengers)

    private val pagingData = PagingData.from(
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.InsideOut)
        )
    )
    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val viewModel by lazy {
        MovieDetailViewModel(
            getMovieDetailsUseCase = getMovieDetailsUseCase,
            savedStateHandle = savedStateHandle.apply {
                whenever(savedStateHandle.get<Int>("movieId")).thenReturn(movie.id)
            }
        )
    }
    @Test
    fun must_notify_uiState_success_when_get_movies_similar_and_movie_details_return_success() = runTest{

        //Given
        whenever(getMovieDetailsUseCase.invoke(any()))
            .thenReturn(ResultData.Success(flowOf(pagingData) to movieDetailsFactory))

        val argumentCaptor = argumentCaptor<IGetMovieDetailsUseCase.Params>()

        //When
        viewModel.uiState.isLoading

        //Then
        verify(getMovieDetailsUseCase).invoke(argumentCaptor.capture())
        assertThat(movieDetailsFactory.id).isEqualTo(argumentCaptor.firstValue.movieId)
        val movieDetails = viewModel.uiState.movieDetails
        val results = viewModel.uiState.results
        assertThat(movieDetails).isNotNull()
        assertThat(results).isNotNull()

    }

    @Test
    fun must_notify_uiState_with_failure_when_get_movie_details_and_returns_exception() = runTest {

        //Given
        val exception = Exception("Oops some must have happened")
        whenever(getMovieDetailsUseCase.invoke(any()))
            .thenReturn(ResultData.Failure(exception))
        //When
        viewModel.uiState.isLoading

        //Then
        val error= viewModel.uiState.error
        assertThat(exception.message).isEqualTo(exception.message)

    }
}