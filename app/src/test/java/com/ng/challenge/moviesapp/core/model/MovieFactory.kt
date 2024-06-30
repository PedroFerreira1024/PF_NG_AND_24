package com.ng.challenge.moviesapp.core.model

import com.ng.challenge.moviesapp.core.domain.model.Movie

class MovieFactory {

    fun create(poster: Poster) = when (poster) {
        Poster.Avengers -> {
            Movie(
                id = 1,
                title = "Avengers",
                voteAvg = 7.2,
                imageUrl = ""
            )
        }
        Poster.InsideOut -> {
            Movie(
                id = 1,
                title = "InsideOut",
                voteAvg = 6.5,
                imageUrl = ""
            )
        }
    }

    sealed class Poster {
        object Avengers: Poster()
        object InsideOut: Poster()
    }
}