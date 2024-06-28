package com.ng.challenge.moviesapp.search_movie.data.mapper

import com.ng.challenge.moviesapp.core.data.remote.model.SearchResult
import com.ng.challenge.moviesapp.core.domain.model.MovieSearch
import com.ng.challenge.moviesapp.core.util.toPostUrl

fun List<SearchResult>.toMovieSearch() = map { searchResult ->
    MovieSearch(
        id = searchResult.id,
        imageUrl = searchResult.posterPath.toPostUrl(),
        voteAvg = searchResult.voteAverage
    )
}
