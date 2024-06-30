package com.ng.challenge.moviesapp.core.paging

import androidx.paging.PagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth.assertThat
import com.ng.challenge.moviesapp.TestDispatcherRule
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.detail_movie.domain.source.IMovieDetailDataSource
import com.ng.challenge.moviesapp.core.model.MovieFactory
import com.ng.challenge.moviesapp.core.model.MoviePagingFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieSimilarPagingSourceTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: IMovieDetailDataSource

    private val movieFactory = MovieFactory()

    private val moviePagingFactory = MoviePagingFactory().create()

    private val moviesSimilarPagingSource by lazy {
        MovieSimilarPagingSource(
            movieId = 1,
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun must_return_success_load_result_when_load_is_called () = runTest {

        //Given
        whenever(remoteDataSource.getMovieSimilar(any(), any()))
            .thenReturn(moviePagingFactory)

        //When
        val result = moviesSimilarPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        val resultExpected = listOf(
            movieFactory.create(MovieFactory.Poster.Avengers),
            movieFactory.create(MovieFactory.Poster.InsideOut)
        )

        //Then
        assertThat(PagingSource.LoadResult.Page(
            data = resultExpected,
            nextKey = null,
            prevKey = null
        )).isEqualTo(result)
    }

    @Test
    fun must_return_a_error_load_result_when_load_is_called() = runTest {

        //Given
        val exception = RuntimeException()
        whenever(remoteDataSource.getMovieSimilar(any(),any()))
            .thenThrow(exception)

        //When
        val result = moviesSimilarPagingSource.load(
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