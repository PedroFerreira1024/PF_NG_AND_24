package com.ng.challenge.moviesapp.core.model

import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.domain.model.MoviePaging

class MoviePagingFactory {

    fun create() = MoviePaging (
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            Movie(
                id = 1,
                title = "Avengers",
                voteAvg = 7.2,
                imageUrl = ""
            ),
            Movie(
                id = 1,
                title = "InsideOut",
                voteAvg = 6.5,
                imageUrl = ""
            )
        )
    )

    sealed class Poster {
        object Avengers: Poster()
        object InsideOut: Poster()
    }
}