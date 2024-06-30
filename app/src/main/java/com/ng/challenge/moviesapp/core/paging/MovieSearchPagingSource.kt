package com.ng.challenge.moviesapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.search_movie.data.mapper.toMovieSearch
import com.ng.challenge.moviesapp.search_movie.domain.source.IMovieSearchDataSource

class MovieSearchPagingSource(
    private val query: String,
    private val remoteDataSource: IMovieSearchDataSource
): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getSearchMovies(page = pageNumber, query = query)

            val movies = response.movies
            val totalPages = response.totalPages
            LoadResult.Page(
                data = movies,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (pageNumber == totalPages) null else pageNumber + 1
            )

        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}