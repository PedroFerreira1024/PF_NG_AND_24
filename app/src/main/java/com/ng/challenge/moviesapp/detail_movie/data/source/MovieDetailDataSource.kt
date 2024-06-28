package com.ng.challenge.moviesapp.detail_movie.data.source

import androidx.compose.ui.text.resolveDefaults
import com.ng.challenge.moviesapp.core.data.remote.MovieService
import com.ng.challenge.moviesapp.core.data.remote.response.MovieResponse
import com.ng.challenge.moviesapp.core.domain.model.MovieDetails
import com.ng.challenge.moviesapp.core.paging.MovieSimilarPagingSource
import com.ng.challenge.moviesapp.core.util.toBackdropUrl
import com.ng.challenge.moviesapp.detail_movie.domain.source.IMovieDetailDataSource
import javax.inject.Inject

class MovieDetailDataSource @Inject constructor(
    private val service: MovieService
) : IMovieDetailDataSource {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = service.getMovie(movieId)
        val genres = response.genres.map { it.name }
        return MovieDetails(
            id = response.id,
            title = response.title,
            overview= response.overview,
            genres = genres,
            releaseDate = response.releaseDate,
            backdropPathUrl = response.backdropPath.toBackdropUrl(),
            voteAvg = response.voteAverage,
            duration = response.runtime,
            voteCount = response.voteCount
        )
    }

    override suspend fun getMovieSimilar(page: Int, movieId: Int): MovieResponse {
        return service.getMoviesSimilar(page = page, movieId = movieId)
    }

    override fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource {
        return MovieSimilarPagingSource(this, movieId = movieId)
    }
}