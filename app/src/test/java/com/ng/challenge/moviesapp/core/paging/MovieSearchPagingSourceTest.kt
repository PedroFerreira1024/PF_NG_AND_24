package com.ng.challenge.moviesapp.core.paging

import androidx.paging.PagingSource
import com.ng.challenge.moviesapp.TestDispatcherRule
import com.google.common.truth.Truth.assertThat
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.model.MovieFactory
import com.ng.challenge.moviesapp.core.model.MoviePagingFactory
import com.ng.challenge.moviesapp.search_movie.domain.source.IMovieSearchDataSource
import com.nhaarman.mockitokotlin2.any
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
class MovieSearchPagingSourceTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: IMovieSearchDataSource

    private val movieSearchFactory = MovieFactory()

    private val movieSearchPagingFactory = MoviePagingFactory().create()

    private val movieSearchPagingSource by lazy {
        MovieSearchPagingSource(
            query = "",
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun must_return_success_load_result_when_load_is_called() = runTest {

        //Given
        whenever(remoteDataSource.getSearchMovies(any(), any()))
            .thenReturn(movieSearchPagingFactory)

        //When
        val result = movieSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val resultExpected = listOf(
            movieSearchFactory.create(MovieFactory.Poster.Avengers),
            movieSearchFactory.create(MovieFactory.Poster.InsideOut)
        )

        //Then
        assertThat(PagingSource.LoadResult.Page(
            data = resultExpected,
            prevKey = null,
            nextKey = null
        )).isEqualTo(result)
    }

    @Test
    fun must_return_a_error_load_result_when_load_is_called() = runTest {

        //Given
        val exception = RuntimeException()
        whenever(remoteDataSource.getSearchMovies(any(),any()))
            .thenThrow(exception)

        //When
        val result = movieSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        //Then
        assertThat(PagingSource.LoadResult.Error<Int, Movie>(exception)).isEqualTo(result)
    }
}