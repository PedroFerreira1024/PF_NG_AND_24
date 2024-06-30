package com.ng.challenge.moviesapp.movie_popular.data.mapper

import com.ng.challenge.moviesapp.core.data.remote.model.MovieResult
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.util.toPostUrl

const val IMAGE_QUALITY_LOW = "w500"
const val IMAGE_QUALITY_HIGH = "w1280"

fun MovieResult.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        releaseDate = releaseDate,
        voteAvg = voteAverage,
        imageUrl = posterPath?.toPostUrl() ?: ""
    )
}