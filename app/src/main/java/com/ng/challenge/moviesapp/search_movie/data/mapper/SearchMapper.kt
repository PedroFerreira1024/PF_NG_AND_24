package com.ng.challenge.moviesapp.search_movie.data.mapper

import com.ng.challenge.moviesapp.core.data.remote.model.SearchResult
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.util.toPostUrl

fun SearchResult.toMovie(): Movie {
    return Movie(
        id = id,
        title = title ?: name ?: "",
        releaseDate = releaseDate ?: firstAirDate ?: "",
        voteAvg = voteAverage,
        imageUrl = posterPath?.toPostUrl() ?: ""
    )
}
