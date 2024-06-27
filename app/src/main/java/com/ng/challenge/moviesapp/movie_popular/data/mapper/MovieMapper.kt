package com.ng.challenge.moviesapp.movie_popular.data.mapper

import com.ng.challenge.moviesapp.core.data.remote.model.MovieResult
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.util.toPostUrl

fun List<MovieResult>.toMovie() = map { movieResult ->
    Movie(
        id = movieResult.id,
        title = movieResult.title,
        voteAvg = movieResult.voteAverage,
        imageUrl = movieResult.posterPath?.toPostUrl() ?: ""
    )
}